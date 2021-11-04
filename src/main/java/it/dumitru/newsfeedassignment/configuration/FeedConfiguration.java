package it.dumitru.newsfeedassignment.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
@EnableConfigurationProperties({ FeedProperties.class })
@RequiredArgsConstructor
public class FeedConfiguration {

    private final FeedProperties feedProperties;

    @Bean("rssDateTimeFormatter")
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern(feedProperties.getExpectedDateFormat());
    }

}
