package it.dumitru.newsfeedassignment.dataproviders.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class FeedDTO {
    private String title;
    private String link;
    private String description;
    private String language;
    private String pubDate;
    private String webMaster;
    @JsonProperty("item")
    private List<NewsItemDTO> newsItems;
}
