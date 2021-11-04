package it.dumitru.newsfeedassignment.core.entity;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class NewsItem {
  private String guid;
  private String title;
  private String description;
  private ZonedDateTime publicationDate;
  private String image;
  private String link;
}
