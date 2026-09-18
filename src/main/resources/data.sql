INSERT INTO media (id, type, title, author, description, created_at) VALUES
    (1, 'BOOK', 'War and Peace', 'Leo Tolstoy', 'An epic novel about Russian society during the Napoleonic Wars.', '2024-01-05 10:00:00'),
    (2, 'BOOK', 'Crime and Punishment', 'Fyodor Dostoevsky', 'The story of a former student Raskolnikov and his inner struggle.', '2024-01-06 11:30:00'),
    (3, 'BOOK', 'The Master and Margarita', 'Mikhail Bulgakov', 'A mystical novel about the devil visiting Moscow in the 1930s.', '2024-01-07 09:15:00'),
    (4, 'BOOK', '1984', 'George Orwell', 'A dystopian novel about a totalitarian society and Big Brother.', '2024-01-08 14:45:00'),
    (5, 'BOOK', 'Harry Potter and the Philosopher''s Stone', 'J. K. Rowling', 'The first book about the young wizard Harry Potter.', '2024-01-09 16:20:00'),
    (6, 'MOVIE', 'The Shawshank Redemption', 'Frank Darabont', 'A drama about hope and friendship within the walls of a prison.', '2024-01-10 12:00:00'),
    (7, 'MOVIE', 'The Godfather', 'Francis Ford Coppola', 'A crime saga about the Corleone family.', '2024-01-11 18:30:00'),
    (8, 'MOVIE', 'Interstellar', 'Christopher Nolan', 'A sci-fi drama about the search for a new home for humanity.', '2024-01-12 20:10:00'),
    (9, 'MOVIE', 'Inception', 'Christopher Nolan', 'A thriller about professional thieves who steal ideas from dreams.', '2024-01-13 21:00:00'),
    (10, 'MOVIE', 'The Green Mile', 'Frank Darabont', 'A drama about a prison guard and an inmate with the gift of healing.', '2024-01-14 19:45:00');

INSERT INTO reviews (id, media_id, rating, text, created_at) VALUES
    (1, 1, 5, 'The greatest work of Russian literature. I re-read it every year.', '2024-02-01 10:00:00'),
    (2, 1, 4, 'Monumental, slow in places, but worth it.', '2024-02-02 11:00:00'),
    (3, 2, 5, 'The psychological depth is stunning. Raskolnikov is an eternal figure.', '2024-02-03 12:00:00'),
    (4, 3, 5, 'Manuscripts don''t burn! One of the best books of the 20th century.', '2024-02-04 13:00:00'),
    (5, 4, 5, 'More relevant than ever. A must-read.', '2024-02-05 14:00:00'),
    (6, 5, 4, 'A warm and cozy book that started an entire universe.', '2024-02-06 15:00:00'),
    (7, 6, 5, 'One of the greatest films ever made. The ending brings me to tears.', '2024-02-07 16:00:00'),
    (8, 7, 5, 'A cinematic classic. Marlon Brando is incomparable.', '2024-02-08 17:00:00'),
    (9, 8, 5, 'A visual and emotional masterpiece. Zimmer''s score is a work of art on its own.', '2024-02-09 18:00:00'),
    (10, 9, 4, 'A mind-bending plot that makes you want to rewatch it again and again.', '2024-02-10 19:00:00');

SELECT setval(pg_get_serial_sequence('media', 'id'), (SELECT MAX(id) FROM media));
SELECT setval(pg_get_serial_sequence('reviews', 'id'), (SELECT MAX(id) FROM reviews));