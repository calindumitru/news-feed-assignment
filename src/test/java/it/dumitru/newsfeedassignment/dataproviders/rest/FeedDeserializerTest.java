package it.dumitru.newsfeedassignment.dataproviders.rest;

import it.dumitru.newsfeedassignment.dataproviders.rest.dto.FeedDTO;
import it.dumitru.newsfeedassignment.dataproviders.rest.dto.NewsItemDTO;
import it.dumitru.newsfeedassignment.dataproviders.rest.dto.NewsItemEnclosureDTO;
import it.dumitru.newsfeedassignment.dataproviders.rest.dto.NewsItemGuidDTO;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootTest
class FeedDeserializerTest {

    @Autowired
    private FeedDeserializer feedDeserializer;

    @SneakyThrows
    @Test
    @DisplayName("Given xml contents representing a valid rss feed with 2 items, when deserializing, DTOs contain expected data")
    void deserialize() {
        val xmlTestResource = Paths.get("src/test/resources", "rssFeed.xml");
        String xmlContents = Files.readString(xmlTestResource);

        final FeedDTO result = feedDeserializer.deserialize(xmlContents);

        Assertions.assertEquals(createExpectedResult(), result);
    }

    private FeedDTO createExpectedResult() {
        FeedDTO feedDTO = new FeedDTO();
        feedDTO.setDescription("NOS Nieuws");
        feedDTO.setTitle("NOS Nieuws");
        feedDTO.setLink("https://nos.nl");
        feedDTO.setLanguage("nl");
        feedDTO.setPubDate("Mon, 01 Nov 2021 15:15:08 +0100");
        feedDTO.setWebMaster("podcast@nos.nl (NOS Podcast Beheer)");

        feedDTO.setNewsItems(List.of(
                createNewsItem1(),
                createNewsItem2()
        ));
        return feedDTO;
    }

    private NewsItemDTO createNewsItem1() {
        NewsItemDTO newsItemDTO = new NewsItemDTO();
        newsItemDTO.setTitle("Coronapas op meer...");
        newsItemDTO.setLink("http://feeds.nos.nl/~r/nosjournaal/~3/y3IQj-yqilI/2403970");
        newsItemDTO.setDescription("desc 1");
        NewsItemEnclosureDTO enclosure = new NewsItemEnclosureDTO();
        enclosure.setType("image/jpeg");
        enclosure.setUrl("https://cdn.nos.nl/image/2021/11/01/799070/1008x567.jpg");
        newsItemDTO.setEnclosure(enclosure);
        newsItemDTO.setPubDate("Mon, 01 Nov 2021 14:46:24 +0100");
        NewsItemGuidDTO newsItemGuidDTO = new NewsItemGuidDTO();
        newsItemGuidDTO.setPermaLink(false);
        newsItemGuidDTO.setContent("https://nos.nl/l/2403970");
        newsItemDTO.setGuid(newsItemGuidDTO);
        return newsItemDTO;
    }

    private NewsItemDTO createNewsItem2() {
        NewsItemDTO newsItemDTO = new NewsItemDTO();
        newsItemDTO.setTitle("Amsterdam wil geen...");
        newsItemDTO.setLink("http://feeds.nos.nl/~r/nosjournaal/~3/P0j0-4z0EU4/2403964");
        newsItemDTO.setDescription("desc 2");
        NewsItemEnclosureDTO enclosure = new NewsItemEnclosureDTO();
        enclosure.setType("image/jpeg");
        enclosure.setUrl("https://cdn.nos.nl/image/2021/11/01/799060/1008x567.jpg");
        newsItemDTO.setEnclosure(enclosure);
        newsItemDTO.setPubDate("Mon, 01 Nov 2021 14:02:35 +0100");
        NewsItemGuidDTO newsItemGuidDTO = new NewsItemGuidDTO();
        newsItemGuidDTO.setPermaLink(false);
        newsItemGuidDTO.setContent("https://nos.nl/l/2403964");
        newsItemDTO.setGuid(newsItemGuidDTO);
        return newsItemDTO;
    }


}