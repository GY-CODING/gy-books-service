package org.gycoding.service.infrastructure.external.googlebooks.repository;

import org.gycoding.logs.logger.Logger;
import org.gycoding.service.infrastructure.external.googlebooks.model.BookEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.gycoding.service.domain.repository.GoogleBooksFacade;
import org.gycoding.service.infrastructure.external.unirest.UnirestFacade;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GoogleBooksFacadeImpl implements GoogleBooksFacade {
    @Value("${google.books.api.url}")
    private String URL;

    @Override
    public List<BookEntity> queryBooks(String title, String author) {

        if (title.isEmpty() && author.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder(URL + "?q=");

        if (!title.isEmpty()) {
            query.append(title);
        }

        if (!author.isEmpty()) {
            if (!title.isEmpty()) {
                query.append("+");
            }
            query.append("inauthor:").append(author);
        }

        final var response = UnirestFacade.get(query.toString());
        var parser = new JSONParser();
        var books = new ArrayList<BookEntity>();

        try {
            for (Object obj : (JSONArray) ((JSONObject) parser.parse(response.getBody())).get("items")) {
                JSONObject jsonObject   = (JSONObject) obj;
                final var bookInfo      = (Map<String, Object>) jsonObject.get("volumeInfo");

                final var persistedBook = BookEntity.builder()
                        .id((String) jsonObject.get("id"))
                        .title((String) bookInfo.get("title"))
                        .authors((List<String>) bookInfo.get("authors"))
                        .cover((String) ((Map<String, Object>) bookInfo.get("imageLinks")).get("thumbnail"))
                        .pageCount(((Long) bookInfo.get("pageCount")).intValue())
                        .publishDate(new SimpleDateFormat("yyyy-MM-dd").parse((String) bookInfo.get("publishedDate")))
                        .build();



                books.add(persistedBook);
            }
        } catch (Exception e) {
            Logger.error("An error has occurred while parsing chat list from metadata response: " + e.getMessage(), e);
        }

        return books;

    }
}
