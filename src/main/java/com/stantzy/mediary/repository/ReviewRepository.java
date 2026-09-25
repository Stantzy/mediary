package com.stantzy.mediary.repository;

import com.stantzy.mediary.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMediaId(Long mediaId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.media.id = :mediaId")
    BigDecimal findAverageRatingByMediaId(@Param("mediaId") Long mediaId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.media.id = :mediaId")
    Long countByMediaId(@Param("mediaId") Long mediaId);
}
