package org.gycoding.books.infrastructure.external.openlibrary.repository;

import org.gycoding.books.domain.exceptions.BooksAPIError;
import org.gycoding.books.domain.model.AuthorMO;
import org.gycoding.books.domain.model.BookMO;
import org.gycoding.books.domain.repository.BookAggregationRepository;
import org.gycoding.books.domain.repository.OpenLibraryFacade;
import org.gycoding.books.infrastructure.external.openlibrary.mapper.OpenLibraryFacadeMapper;
import org.gycoding.books.infrastructure.external.unirest.UnirestFacade;
import org.gycoding.exceptions.model.APIException;
import org.gycoding.logs.logger.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OpenLibraryFacadeImpl implements OpenLibraryFacade {
    @Autowired
    private OpenLibraryFacadeMapper mapper;

    @Autowired
    private BookAggregationRepository bookAggregationRepository;

    @Value("${openlibrary.api.url}")
    private String URL;

    @Override
    public List<BookMO> queryBooks(String query) {
        if (query.isEmpty()) {
            return null;
        }

        final var response = UnirestFacade.get(String.format(URL + "/search.json?q=%s", query));
        var books = new ArrayList<BookMO>();

        try {
            var parser = new JSONParser();

            for (Object obj : (JSONArray) ((JSONObject) parser.parse(response.getBody())).get("docs")) {
                JSONObject book   = (JSONObject) obj;

                final var work = (JSONObject) parser.parse(
                        UnirestFacade.get(
                                String.format(URL + "%s.json", (String) book.get("key")))
                                .getBody()
                );

                final var id = bookAggregationRepository.getId(((String) book.get("key")).replace("/works/", ""))
                                .orElseThrow(() -> new APIException(
                                        BooksAPIError.SERVER_ERROR.getCode(),
                                        BooksAPIError.SERVER_ERROR.getMessage(),
                                        BooksAPIError.SERVER_ERROR.getStatus()
                                ));

                books.add(
                        mapper.toBookMO(
                                id,
                                work,
                                book,
                                getAuthors((List<String>) book.get("author_key"))
                        )
                );
            }
        } catch (Exception e) {
            Logger.error("An error has occurred while parsing a book from Open Library API into GY Books API: " + e.getMessage(), e);
        }

        return books;
    }

    private List<AuthorMO> getAuthors(List<String> keys) {
        if (keys == null || keys.isEmpty()) {
            return List.of();
        }

        final var authors = new ArrayList<AuthorMO>();

        keys.forEach(key -> {
            final var response = UnirestFacade.get(String.format(URL + "/authors/%s.json", key));

            var parser = new JSONParser();

            try {
                final var authorBody = (JSONObject) parser.parse(response.getBody());

                authors.add(AuthorMO.builder()
                        .name((String) authorBody.get("personal_name"))
                        .build()
                );
            } catch (Exception e) {
                Logger.error("An error has occurred while parsing an author from Open Library API into GY Books API: " + e.getMessage(), e);
            }
        });

        return authors;
    }
}
