package org.gycoding.books.infrastructure.external.openlibrary.mapper;

import org.gycoding.books.domain.model.AuthorMO;
import org.gycoding.books.domain.model.BookMO;
import org.json.simple.JSONObject;
import org.mapstruct.Mapper;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = { UUID.class, SimpleDateFormat.class })
public interface OpenLibraryFacadeMapper {
    default BookMO toBookMO(UUID id, JSONObject work, JSONObject book, List<AuthorMO> authors) {
        final var COVER_URL = "https://covers.openlibrary.org/b/olid/%s-L.jpg";

        final var DEFAULT_COVER = "https://github.com/GY-CODING/img-repo/blob/main/gy-books/none.png?raw=true";
        final var DEFAULT_DESCRIPTION = "No description available.";

        return BookMO.builder()
                .id(id)
                .title((String) work.get("title"))
                .description(work.containsKey("description") ? (String) ((HashMap<String, Object>) work.get("description")).get("value") : DEFAULT_DESCRIPTION)
                .authors(authors)
                .cover(book.containsKey("cover_edition_key") ? String.format(COVER_URL, (String) book.get("cover_edition_key")) : DEFAULT_COVER)
                .build();
    }
}
