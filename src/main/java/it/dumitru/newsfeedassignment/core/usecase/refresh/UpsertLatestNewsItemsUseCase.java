package it.dumitru.newsfeedassignment.core.usecase.refresh;

import it.dumitru.newsfeedassignment.core.entity.NewsItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UpsertLatestNewsItemsUseCase {

    private static final int MAX_DAYS_TO_KEEP = 5;

    private final GetLatestNewsItems getLatestNewsItems;
    private final RemoveOldNewsItems removeOldNewsItems;
    private final StoreNewsItems storeNewsItems;

    public void retrieveAndUpsert(final String url) {
        log.info("Reading news from rss feed..");
        List<NewsItem> newsItems = getLatestNewsItems.getNewsItemsFromRssFeed(url);

        log.info("Storing {} news items", newsItems.size());
        storeNewsItems.storeItems(newsItems);

        //avoid accumulating too many news items
        removeOldNewsItems.deleteOlderThan(calculateThresholdDate());
    }

    private ZonedDateTime calculateThresholdDate() {
        long daysToSubtract = MAX_DAYS_TO_KEEP - 1L; //consider the current day as full.
        LocalDate minus = LocalDate.now().minus(daysToSubtract, ChronoUnit.DAYS);
        return ZonedDateTime.of(minus, LocalTime.MIDNIGHT, ZoneId.systemDefault());
    }

}
