package it.dumitru.newsfeedassignment.dataproviders.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.dumitru.newsfeedassignment.dataproviders.rest.dto.FeedDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeedDeserializer {

    @Qualifier("feedObjectMapper")
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public FeedDTO deserialize(final String xmlString) {
        JSONObject jsonObject = XML.toJSONObject(xmlString);
        JSONObject rssContents = jsonObject.getJSONObject("rss");
        JSONObject channelContents = rssContents.getJSONObject("channel");
        return objectMapper.readValue(channelContents.toString(), FeedDTO.class);
    }

}
