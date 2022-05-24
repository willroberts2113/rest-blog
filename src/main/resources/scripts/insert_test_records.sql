INSERT INTO users (username, password, email, role, created_at) # TODO: created_at column included on seeder
VALUES ('test_user', 'test123', 'test@test.com', 'USER', NOW()); # TODO: insert NOW() for created_at column

INSERT INTO posts (user_id, title, content) VALUES (1, 'Babys First Post', 'Do not be alarmed. This is only a test.');

INSERT INTO tags (name) VALUES ('test_tag');

INSERT INTO post_tags (post_id, tag_id) VALUES (1, 1);

SELECT * FROM users;

SELECT * FROM posts;
SELECT * FROM post_tags;