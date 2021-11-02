package it.dumitru.newsfeedassignment.entrypoints.job;

import it.dumitru.newsfeedassignment.dataproviders.database.NewsItemEntity;
import it.dumitru.newsfeedassignment.dataproviders.database.NewsItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReadEntitiesJob {

    final NewsItemRepository repository;

    @Scheduled(fixedRate = 5000)
    public void triggerUpdateFromFeed() {
        Iterable<NewsItemEntity> all = repository.findAll();
        log.info(all);
    }
}
