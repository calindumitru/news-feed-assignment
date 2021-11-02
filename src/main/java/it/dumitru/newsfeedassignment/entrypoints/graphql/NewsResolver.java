package it.dumitru.newsfeedassignment.entrypoints.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import it.dumitru.newsfeedassignment.core.usecase.retrieve.RetrieveAllNewsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsResolver /*implements GraphQLQueryResolver*/ {

    private final RetrieveAllNewsUseCase retrieveAllNewsUseCase;

    //todo implementation
}
