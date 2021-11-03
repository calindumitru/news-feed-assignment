package it.dumitru.newsfeedassignment.dataproviders.database;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NewsItemRepository extends PagingAndSortingRepository<NewsItemEntity, String> {

    @Query("select guid from NewsItemEntity")
    Set<String> getExistingGuids();
}
