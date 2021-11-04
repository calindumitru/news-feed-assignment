package it.dumitru.newsfeedassignment.dataproviders.rest.dto;

import lombok.Data;

@Data
public class NewsItemEnclosureDTO {
  private String type;
  private String url;
}
