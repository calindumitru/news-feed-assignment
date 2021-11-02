package it.dumitru.newsfeedassignment.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ FeedProperties.class })
public class FeedConfiguration {
}
