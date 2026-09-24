package com.stantzy.mediary.utils;

import com.stantzy.mediary.domain.Media;
import com.stantzy.mediary.domain.Review;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.function.Supplier;

public class ResponseWrapper {
    public static <T> ResponseEntity<T> handleOk(Supplier<T> action) {
        try {
            return ResponseEntity.ok(action.get());
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public static <T> ResponseEntity<T> handleCreated(Supplier<T> action) {
        try {
            T result = action.get();
            URI location = null;

            if(result instanceof Review) {
                location = URI.create(
                    "/api/reviews/" + ((Review) result).getId()
                );
            } else if(result instanceof Media) {
                location = URI.create(
                    "/api/media/" + ((Media) result).getId()
                );
            } else {
                throw new IllegalStateException(
                    "Unknown type of entity: "
                        + result.getClass().getSimpleName()
                );
            }

            return ResponseEntity.created(location).body(result);
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public static <T> ResponseEntity<T> handleNoContent(Runnable action) {
        try {
            action.run();
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
