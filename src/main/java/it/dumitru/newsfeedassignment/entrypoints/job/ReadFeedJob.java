package it.dumitru.newsfeedassignment.entrypoints.job;


import it.dumitru.newsfeedassignment.configuration.FeedProperties;
import it.dumitru.newsfeedassignment.core.usecase.refresh.RefreshNewsItemsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReadFeedJob {

    private final FeedProperties feedProperties;
    private final RefreshNewsItemsUseCase refreshNewsItemsUseCase;

    @Scheduled(fixedRate = 5000)
    public void triggerUpdateFromFeed() {
        refreshNewsItemsUseCase.refresh(feedProperties.getFeedUrl());
    }
}
