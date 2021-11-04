package it.dumitru.newsfeedassignment.entrypoints.job;


import it.dumitru.newsfeedassignment.configuration.FeedProperties;
import it.dumitru.newsfeedassignment.core.usecase.refresh.UpsertLatestNewsItemsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReadFeedJob {

  private final FeedProperties feedProperties;
  private final UpsertLatestNewsItemsUseCase upsertLatestNewsItemsUseCase;

  @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
  public void triggerUpdateFromFeed() {
    upsertLatestNewsItemsUseCase.retrieveAndUpsert(feedProperties.getFeedUrl());
  }
}
