package it.dumitru.newsfeedassignment.core.usecase.refresh;

import it.dumitru.newsfeedassignment.core.entity.NewsItem;
import it.dumitru.newsfeedassignment.core.entity.NewsItemsOperations;
import it.dumitru.newsfeedassignment.dataproviders.GetLatestNewsItems;
import it.dumitru.newsfeedassignment.dataproviders.database.NewsItemDataProvider;
import it.dumitru.newsfeedassignment.dataproviders.database.NewsItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefreshNewsItemsUseCase {
    private static final long ITEMS_TO_KEEP = 10L;

    private final GetLatestNewsItems getLatestNewsItems;
    private final NewsItemDataProvider newsItemDataProvider;
    private final NewsItemRepository repository; //todo remove

    public void refresh(final String url) {
        List<NewsItem> newsItems = getLatestNewsItems.getNewsItems(url);

        final List<NewsItem> itemsToKeep = newsItems.stream()
                .sorted(Comparator.comparing(NewsItem::getPublicationDate).reversed())
                .limit(ITEMS_TO_KEEP)
                .collect(Collectors.toList());
        final NewsItemsOperations newsItemsOperations = determineOperations(itemsToKeep);
        newsItemDataProvider.storeItems(newsItemsOperations);
    }

    private NewsItemsOperations determineOperations(List<NewsItem> itemsToKeep) {
        final Set<String> existingGuids = repository.getExistingGuids(); //todo move to dataprovider / service
        final NewsItemsOperations newsItemsOperations = new NewsItemsOperations();
        newsItemsOperations.setToUpsert(itemsToKeep);
        newsItemsOperations.setToRemove(calculateIdsToRemove(itemsToKeep, existingGuids));
        return newsItemsOperations;
    }

    private Set<String> calculateIdsToRemove(List<NewsItem> itemsToKeep, Set<String> existingGuids) {
        final Set<String> idsToRemove = new HashSet<>(existingGuids);
        List<String> newGuids = itemsToKeep.stream()
                .map(NewsItem::getGuid)
                .collect(Collectors.toList());
        newGuids.forEach(idsToRemove::remove);
        return idsToRemove;
    }
}
