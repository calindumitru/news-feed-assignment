package it.dumitru.newsfeedassignment.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Log4j2
@Configuration
public class AfterInitWelcomeConfiguration {

    @EventListener(ApplicationReadyEvent.class)
    public void printPlaygroundUrl() {
        log.info("*********** Access graphQL playground via:  http://localhost:8080/playground  ***********");
    }
}
