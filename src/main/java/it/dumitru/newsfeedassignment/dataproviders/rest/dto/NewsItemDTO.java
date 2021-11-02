package it.dumitru.newsfeedassignment.dataproviders.rest.dto;

import lombok.Data;

@Data
public class NewsItemDTO {
    private String title;
    private String link;
    private String description;
    private NewsItemEnclosureDTO enclosure;
    private String pubDate;
    private NewsItemGuidDTO guid;
}
