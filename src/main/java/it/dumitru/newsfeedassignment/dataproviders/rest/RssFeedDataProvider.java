package it.dumitru.newsfeedassignment.dataproviders.rest;


import it.dumitru.newsfeedassignment.core.entity.NewsItem;
import it.dumitru.newsfeedassignment.dataproviders.GetLatestNewsItems;
import it.dumitru.newsfeedassignment.dataproviders.rest.dto.FeedDTO;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import lombok.SneakyThrows;

@Service
public class RssFeedDataProvider implements GetLatestNewsItems {

  private final RestTemplate restTemplate;
  private final FeedDeserializer feedDeserializer;

  //Mon, 01 Nov 2021 15:15:08 +0100
  private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z");

  public RssFeedDataProvider(FeedDeserializer feedDeserializer) {
    this.feedDeserializer = feedDeserializer;
    this.restTemplate = new RestTemplate();
  }

  @SneakyThrows
  public List<NewsItem> getNewsItems(final String rssFeedUrl) {
    URI uri = URI.create(rssFeedUrl);
    final String xmlString = restTemplate.getForObject(uri, String.class);
    final FeedDTO feedDTO = feedDeserializer.deserialize(xmlString);
    return feedDTO.getNewsItems().stream()
        .map(dto -> NewsItem.builder()
            .guid(dto.getGuid().getContent())
            .image(dto.getEnclosure().getUrl())
            .publicationDate(parse(dto.getPubDate())) //fixme add conversion
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
