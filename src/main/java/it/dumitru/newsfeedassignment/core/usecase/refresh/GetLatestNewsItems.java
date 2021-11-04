package it.dumitru.newsfeedassignment.core.usecase.refresh;

import it.dumitru.newsfeedassignment.core.entity.NewsItem;

import java.util.List;

public interface GetLatestNewsItems {

    List<NewsItem> getNewsItemsFromRssFeed(final String url);
}
