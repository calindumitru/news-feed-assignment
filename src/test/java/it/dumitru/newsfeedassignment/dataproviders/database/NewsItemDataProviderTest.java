package it.dumitru.newsfeedassignment.dataproviders.database;


import it.dumitru.newsfeedassignment.core.entity.NewsItem;
import it.dumitru.newsfeedassignment.core.entity.NewsItemsOperations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.probablyfine.matchers.OptionalMatchers;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.probablyfine.matchers.Java8Matchers.where;
import static uk.co.probablyfine.matchers.OptionalMatchers.empty;

@ExtendWith(SpringExtension.class)
@EnableJpaRepositories(basePackageClasses = NewsItemRepository.class)
@SpringBootTest
@EntityScan(basePackageClasses = NewsItemEntity.class)
class NewsItemDataProviderTest {

    @Autowired
    NewsItemRepository repository;

    @Autowired
    NewsItemDataProvider under_test;

    @Test
    void delete() {
        addItem("removeId");
        NewsItemsOperations newsItemsOperations = new NewsItemsOperations();
        newsItemsOperations.setToRemove(Set.of("removeId"));

        under_test.storeItems(newsItemsOperations);

        assertThat(repository.count(), is(0L));
    }

    @Test
    void add() {
        NewsItemsOperations newsItemsOperations = new NewsItemsOperations();
        newsItemsOperations.setToUpsert(List.of(
                NewsItem.builder().guid("addId").build()
        ));

        under_test.storeItems(newsItemsOperations);

        assertThat(repository.count(), is(1L));
        assertThat(repository.findById("addId"), not(empty()));
    }

    @Test
    void update() {
        addItem("updateId", "initial description");

        NewsItemsOperations newsItemsOperations = new NewsItemsOperations();
        newsItemsOperations.setToUpsert(List.of(
                NewsItem.builder().guid("updateId")
                        .description("updated description")
                        .build()
        ));

        under_test.storeItems(newsItemsOperations);

        assertThat(repository.count(), is(1L));
        assertThat(repository.findById("updateId"),
                OptionalMatchers.contains(where(NewsItemEntity::getDescription, is("updated description")))
        );
    }

    private void addItem(final String id) {
        addItem(id, null);
    }

    private void addItem(final String id, final String description) {
        NewsItemEntity newsItemEntity = new NewsItemEntity();
        newsItemEntity.setGuid(id);
        newsItemEntity.setDescription(description);
        repository.save(newsItemEntity);
    }


}