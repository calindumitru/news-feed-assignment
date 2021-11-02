package it.dumitru.newsfeedassignment.dataproviders;

import it.dumitru.newsfeedassignment.core.entity.NewsItem;

import java.util.List;

public interface GetLatestNewsItems {

    List<NewsItem> getNewsItems(final String url);
}
