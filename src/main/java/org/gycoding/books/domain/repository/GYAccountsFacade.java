package org.gycoding.books.domain.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GYAccountsFacade {
    String getUserId(String profileId);
    UUID getProfileId(String userId);
}
