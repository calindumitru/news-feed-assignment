package it.dumitru.newsfeedassignment.dataproviders.rest;


import it.dumitru.newsfeedassignment.core.entity.NewsItem;
import it.dumitru.newsfeedassignment.core.usecase.refresh.GetLatestNewsItems;
import it.dumitru.newsfeedassignment.dataproviders.rest.dto.FeedDTO;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RssFeedDataProvider implements GetLatestNewsItems {

  private final RestTemplate restTemplate;
  private final FeedDeserializer feedDeserializer;

  private final DateTimeFormatter dateTimeFormatter;

  public RssFeedDataProvider(FeedDeserializer feedDeserializer,
                             @Qualifier("rssDateTimeFormatter") final DateTimeFormatter expectedDateTimeFormat) {
    this.feedDeserializer = feedDeserializer;
    this.dateTimeFormatter = expectedDateTimeFormat;
    this.restTemplate = new RestTemplate();
  }

  public List<NewsItem> getNewsItemsFromRssFeed(final String rssFeedUrl) {
    URI uri = URI.create(rssFeedUrl);
    final String xmlString = restTemplate.getForObject(uri, String.class);
    final FeedDTO feedDTO = feedDeserializer.deserialize(xmlString);
    return feedDTO.getNewsItems().stream()
        .map(dto -> NewsItem.builder()
            .guid(dto.getGuid().getContent())
            .image(dto.getEnclosure() != null ? dto.getEnclosure().getUrl() : null)
            .publicationDate(parse(dto.getPubDate()))
            .title(dto.getTitle())
            .description(dto.getDescription())
            .link(dto.getLink())
            .build())
        .collect(Collectors.toList());
  }

  private ZonedDateTime parse(final String pubDate) {
    return ZonedDateTime.parse(pubDate, dateTimeFormatter);
  }

}
