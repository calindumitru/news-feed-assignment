package it.dumitru.newsfeedassignment.dataproviders.database;

import it.dumitru.newsfeedassignment.core.entity.NewsItem;
import it.dumitru.newsfeedassignment.core.entity.NewsItemsOperations;
import it.dumitru.newsfeedassignment.core.usecase.retrieve.GetAllNewsItems;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsItemDataProvider implements GetAllNewsItems {

  private final NewsItemRepository repository;

  public void storeItems(final NewsItemsOperations newsItemsOperations) {
    List<NewsItem> toAdd = newsItemsOperations.getToUpsert();
    final List<NewsItemEntity> entities = toAdd.stream().map(newsItem -> NewsItemEntity.builder()
        .guid(newsItem.getGuid())
        .title(newsItem.getTitle())
        .description(newsItem.getDescription())
        .publicationDate(newsItem.getPublicationDate())
        .image(newsItem.getImage())
        .link(newsItem.getLink())
        .build()).collect(Collectors.toList());
    repository.saveAll(entities);
    repository.deleteAllById(newsItemsOperations.getToRemove());
  }

  @Override
  public List<NewsItem> getAllNewsItems(final int page, final int size) {
    return repository.findAll(createPageRequest(page, size))
        .map(
            entity -> NewsItem.builder()
                .guid(entity.getGuid())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .publicationDate(entity.getPublicationDate())
                .image(entity.getImage())
                .link(entity.getLink())
                .build()
        )
        .getContent();
  }

  @NotNull
  private PageRequest createPageRequest(final int page, final int size) {
    return PageRequest.of(
        page,
        size,
        Sort.by(Sort.Direction.DESC, "publicationDate")
    );
  }
}
