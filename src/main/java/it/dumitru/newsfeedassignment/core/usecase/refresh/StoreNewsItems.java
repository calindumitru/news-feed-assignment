package it.dumitru.newsfeedassignment.core.usecase.refresh;

import it.dumitru.newsfeedassignment.core.entity.NewsItem;

import java.util.List;

public interface StoreNewsItems {
    void storeItems(List<NewsItem> newsItems);
}
