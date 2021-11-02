package it.dumitru.newsfeedassignment.core.usecase.retrieve;

import it.dumitru.newsfeedassignment.core.entity.NewsItem;

import java.util.List;

public interface GetAllNewsItems {

    List<NewsItem> getAllNewsItems();
}
