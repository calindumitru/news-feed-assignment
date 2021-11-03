package it.dumitru.newsfeedassignment.entrypoints.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;

import it.dumitru.newsfeedassignment.core.entity.NewsItem;
import it.dumitru.newsfeedassignment.core.usecase.retrieve.RetrieveAllNewsUseCase;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsResolver implements GraphQLQueryResolver {

  private final RetrieveAllNewsUseCase retrieveAllNewsUseCase;

  public List<NewsItem> latestNewsItems(final int page, final int size) {
    return retrieveAllNewsUseCase.retrieve(page, size);
  }
}
