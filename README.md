# Mediary

A personal library of reviews and ratings for books, movies, and other media. A REST API for storing, cataloging, and browsing your impressions.

This project is under development. Features and API may change.

## About

Mediary is a personal service for maintaining a media library: books, movies, and (in the future) TV series, games, and more.

It allows you to:
- add media to your collection;
- leave ratings and write reviews;
- filter and search your collection;
- track status.

The project is built as a pet project for practicing Java and the Spring ecosystem.

## Tech Stack

- Java 21
- Spring Boot
- PostgreSQL
- Docker
- Maven
- JUnit 5

## Endpoints

The API will be available at `http://localhost:8080/`.

### Media
| Method | Endpoint          | Description     | Success            |
| ------ | ----------------- | --------------- | ------------------ |
| GET    | `/api/media`      | List all media  | `200`              |
| GET    | `/api/media/{id}` | Get media by ID | `200`              |
| POST   | `/api/media`      | Create media    | `201` + `Location` |
| PUT    | `/api/media/{id}` | Update media    | `200`              |
| DELETE | `/api/media/{id}` | Delete media    | `204`              |

### Reviews
| Method | Endpoint                    | Description                       | Success            |
| ------ | --------------------------- | --------------------------------- | ------------------ |
| GET    | `/api/reviews`              | List all reviews                  | `200`              |
| GET    | `/api/reviews?mediaId={id}` | Reviews for a specific media item | `200`              |
| GET    | `/api/reviews/{id}`         | Get review by ID                  | `200`              |
| POST   | `/api/reviews`              | Create review                     | `201` + `Location` |
| PUT    | `/api/reviews/{id}`         | Update review                     | `200`              |
| DELETE | `/api/reviews/{id}`         | Delete review                     | `204`              |

### Error handling

All errors are handled centrally and returned in a unified format, for example:

```json
{
  "message": "title: must not be blank; year: must be positive",
  "timestamp": "2026-10-14T12:15:00Z"
}
```

Multiple validation errors are joined into a single message with `;`.
