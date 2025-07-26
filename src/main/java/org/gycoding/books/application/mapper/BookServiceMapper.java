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

    @Mapping(target = "userData.rating", expression = "java(checkStatus(userDataIDTO))")
    BookMO toMO(BookIDTO book);

    default Number checkStatus(UserDataIDTO userData) {
        return BookStatus.READ.equals(userData.status()) ? userData.rating() : 0.0;
    }
}
