INSERT INTO users (username, password, email, role, created_at) # TODO: created_at column included on seeder
VALUES ('test_user', 'test123', 'test@test.com', 'USER', NOW()); # TODO: insert NOW() for created_at column

INSERT INTO posts (user_id, title, content) VALUES (1, 'Babys First Post', 'Do not be alarmed. This is only a test.');

INSERT INTO categories (name)
VALUES ('MUSIC'),
       ('FOOD'),
       ('PROGRAMMING');

INSERT INTO post_category (post_id, category_id) VALUES (1, 3);

SELECT * FROM users;

SELECT * FROM posts;
SELECT * FROM post_category;