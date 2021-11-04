package it.dumitru.newsfeedassignment.core.usecase.retrieve;

import it.dumitru.newsfeedassignment.core.entity.NewsItem;

import java.util.List;

public interface RetrieveStoredNewsItems {

    List<NewsItem> getNewsItems(final int page, final int size);
}
