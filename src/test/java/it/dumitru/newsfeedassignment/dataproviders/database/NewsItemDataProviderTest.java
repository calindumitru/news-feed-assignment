package it.dumitru.newsfeedassignment.dataproviders.database;


import it.dumitru.newsfeedassignment.core.entity.NewsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.probablyfine.matchers.Java8Matchers.where;
import static uk.co.probablyfine.matchers.OptionalMatchers.contains;
import static uk.co.probablyfine.matchers.OptionalMatchers.empty;

@ExtendWith(SpringExtension.class)
@EnableJpaRepositories(basePackageClasses = NewsItemRepository.class)
@SpringBootTest
@EntityScan(basePackageClasses = NewsItemEntity.class)
@Transactional
class NewsItemDataProviderTest {

    @Autowired
    NewsItemRepository repository;

    @Autowired
    NewsItemDataProvider under_test;

    private static final ZonedDateTime NOT_NULL_DATE = ZonedDateTime.now();

    @DisplayName("Given empty repository, when storing an item, it should add a record")
    @Test
    void add() {
        List<NewsItem> toAdd = List.of(
                NewsItem.builder().guid("addId")
                        .title("title")
                        .publicationDate(NOT_NULL_DATE)
                        .build()
        );

        under_test.storeItems(toAdd);

        assertThat(repository.count(), is(1L));
        assertThat(repository.findById("addId"), not(empty()));
    }

    @DisplayName("Given an existing record, when storing an item with the same id, it should update the existing record")
    @Test
    void update() {
        addItem("updateId", "initial description", "initial title");
        List<NewsItem> toUpdate = List.of(
                NewsItem.builder()
                        .guid("updateId")
                        .description("updated description")
                        .title("updated title")
                        .publicationDate(NOT_NULL_DATE)
                        .build()
        );

        under_test.storeItems(toUpdate);

        assertThat(repository.count(), is(1L));
        assertThat(repository.findById("updateId"),
                contains(allOf(
                        where(NewsItemEntity::getDescription, is("updated description")),
                        where(NewsItemEntity::getTitle, is("updated title"))
                ))
        );
    }

    @DisplayName("Given one record published today and one record published two days ago," +
            " when deleting records older than yesterday," +
            " it should delete only the published two days ago")
    @Test
    void deleteOlderThan() {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime yesterday = now.minus(1, ChronoUnit.DAYS);
        ZonedDateTime twoDaysAgo = now.minus(2, ChronoUnit.DAYS);
        addItem("newItemId", now);
        addItem("oldItemId", twoDaysAgo);

        under_test.deleteOlderThan(yesterday);

        assertThat(repository.count(), is(1L));
        assertThat(repository.findAll(), hasItem(where(NewsItemEntity::getGuid, is("newItemId"))));
    }

    private void addItem(final String id, final ZonedDateTime dateTime) {
        NewsItemEntity newsItemEntity = new NewsItemEntity();
        newsItemEntity.setGuid(id);
        newsItemEntity.setPublicationDate(dateTime);
        newsItemEntity.setTitle("notNull");
        repository.save(newsItemEntity);
    }

    private void addItem(final String id, final String description, final String title) {
        NewsItemEntity newsItemEntity = new NewsItemEntity();
        newsItemEntity.setGuid(id);
        newsItemEntity.setDescription(description);
        newsItemEntity.setTitle(title);
        newsItemEntity.setPublicationDate(NOT_NULL_DATE);
        repository.save(newsItemEntity);
    }


}