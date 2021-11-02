package it.dumitru.newsfeedassignment.core.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class NewsItemsOperations {

    List<NewsItem> toUpsert = new ArrayList<>();
    Set<String> toRemove = new HashSet<>();
}
