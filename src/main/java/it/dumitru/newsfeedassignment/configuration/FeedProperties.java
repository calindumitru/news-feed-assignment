package it.dumitru.newsfeedassignment.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "rss-app")
@Validated
@Data
public class FeedProperties {

    @NotEmpty
    private String feedUrl;

    @NotEmpty
    private String expectedDateFormat;

}
