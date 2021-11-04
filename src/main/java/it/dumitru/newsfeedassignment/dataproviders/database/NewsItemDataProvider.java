package it.dumitru.newsfeedassignment.dataproviders.database;

import it.dumitru.newsfeedassignment.core.entity.NewsItem;
import it.dumitru.newsfeedassignment.core.usecase.refresh.RemoveOldNewsItems;
import it.dumitru.newsfeedassignment.core.usecase.refresh.StoreNewsItems;
import it.dumitru.newsfeedassignment.core.usecase.retrieve.RetrieveStoredNewsItems;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsItemDataProvider implements RetrieveStoredNewsItems, StoreNewsItems, RemoveOldNewsItems {

  private final NewsItemRepository repository;

  @Override
  public void storeItems(final List<NewsItem> newsItems) {
    final List<NewsItemEntity> entities = newsItems.stream().map(newsItem -> NewsItemEntity.builder()
            .guid(newsItem.getGuid())
            .title(newsItem.getTitle())
            .description(newsItem.getDescription())
            .publicationDate(newsItem.getPublicationDate())
            .image(newsItem.getImage())
            .link(newsItem.getLink())
            .build()).collect(Collectors.toList());
    repository.saveAll(entities);
  }

  @Override
  public void deleteOlderThan(final ZonedDateTime zonedDateTime) {
    repository.deleteAllByPublicationDateBefore(zonedDateTime);
  }

  @Override
  public List<NewsItem> getNewsItems(final int page, final int size) {
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
