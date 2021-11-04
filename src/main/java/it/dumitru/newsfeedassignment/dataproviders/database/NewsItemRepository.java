package it.dumitru.newsfeedassignment.dataproviders.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface NewsItemRepository extends PagingAndSortingRepository<NewsItemEntity, String> {

    void deleteAllByPublicationDateBefore(ZonedDateTime dateTime);

}
