package it.dumitru.newsfeedassignment.core.usecase.retrieve;

import it.dumitru.newsfeedassignment.core.entity.NewsItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveAllNewsUseCase {

    private final GetAllNewsItems getAllNewsItems;

    public List<NewsItem> retrieve(final int page, final int size) {
        return getAllNewsItems.getAllNewsItems(page, size);
    }
}
