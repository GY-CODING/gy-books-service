package org.gycoding.books.application.mapper;

import org.gycoding.books.application.dto.in.BookIDTO;
import org.gycoding.books.application.dto.in.UserDataIDTO;
import org.gycoding.books.application.dto.out.BookODTO;
import org.gycoding.books.domain.model.BookMO;
import org.gycoding.books.domain.model.BookStatus;
import org.gycoding.books.domain.model.UserDataMO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookServiceMapper {
    BookODTO toODTO(BookMO book);

    @Mapping(target = "userData.rating", expression = "java(checkRating(userDataIDTO))")
    @Mapping(target = "userData.status", expression = "java(checkStatus(userDataIDTO))")
    BookMO toMO(BookIDTO book);

    default Number checkRating(UserDataIDTO userData) {
        if(userData.progress() == null) return userData.rating();

        return BookStatus.READ.equals(userData.status()) ? userData.rating() : 0.0;
    }

    default BookStatus checkStatus(UserDataIDTO userData) {
        if(userData.progress() == null) return userData.status();

        return userData.progress().equals(1) ? BookStatus.READ : userData.status();
    }
}
