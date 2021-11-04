package it.dumitru.newsfeedassignment.entrypoints.graphql;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import it.dumitru.newsfeedassignment.core.entity.NewsItem;
import it.dumitru.newsfeedassignment.core.usecase.retrieve.RetrieveAllNewsUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@GraphQLTest
class NewsResolverTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    private RetrieveAllNewsUseCase retrieveAllNewsUseCase;

    @BeforeEach
    void beforeEach() {
        when(retrieveAllNewsUseCase.retrieve(anyInt(), anyInt())).thenReturn(List.of(
                NewsItem.builder()
                        .guid("id")
                        .title("title value")
                        .description("description value")
                        .image("image value")
                        .link("link value")
                        .build()
        ));
    }


    @DisplayName("Given one news item, when executing the latestNewsItems query, the response should contain one element with the data matching the news item")
    @Test
    void latestNewsItems() throws IOException {
        final GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/retrieve-news.graphql");

        assertThat(response.isOk(), is(true));
        response.assertThatField("$.data.latestNewsItems")
                .asListOf(NewsItem.class)
                .containsExactly(createExpectedResult());
    }

    private NewsItem createExpectedResult() {
        return NewsItem.builder()
                .guid("id")
                .title("title value")
                .description("description value")
                .image("image value")
                .link("link value")
                .build();
    }
}