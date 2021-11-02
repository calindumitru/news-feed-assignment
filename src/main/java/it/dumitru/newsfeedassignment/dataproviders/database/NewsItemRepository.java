package it.dumitru.newsfeedassignment.dataproviders.database;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NewsItemRepository extends CrudRepository<NewsItemEntity, String> {

    @Query("select guid from NewsItemEntity")
    Set<String> getExistingGuids();

//    void removeAllByGuidIn(Set<String> guids);
}
