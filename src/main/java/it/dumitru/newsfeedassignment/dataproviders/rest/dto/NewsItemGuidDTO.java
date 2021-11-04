package it.dumitru.newsfeedassignment.dataproviders.rest.dto;

import lombok.Data;

@Data
public class NewsItemGuidDTO {
  private boolean isPermaLink;
  private String content;
}
