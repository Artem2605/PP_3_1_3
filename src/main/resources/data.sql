DELETE from users_roles;
DELETE from roles;
DELETE from users;
INSERT INTO roles (name)
values ('ADMIN'),
       ('USER');
INSERT INTO users (last_name, password, username, year)
VALUES ('admin', '$2a$10$Q/b7EGrTR1TUhLVJxifxieNWm0PBtt0wW0HXgzaDOYcHwv6nD9Pg2', 'admin', 777);
INSERT INTO users_roles
SET user_id=(SELECT id FROM users WHERE username = 'admin'),
    role_id = (SELECT id FROM roles WHERE name = 'ADMIN');
INSERT INTO users_roles
SET user_id=(SELECT id FROM users WHERE username = 'admin'),
    role_id = (SELECT id FROM roles WHERE name = 'USER');
INSERT INTO users (last_name, password, username, year)
VALUES ('user', '$2a$12$3sa6ec/eVkjtk0Fsp4p8RuwDNK5EbTHlZ1srZjz2adXEJGEzLx1GC', 'user', 2000);
INSERT INTO users_roles
SET user_id=(SELECT id FROM users WHERE username = 'user'),
    role_id = (SELECT id FROM roles WHERE name = 'USER');
INSERT INTO users (last_name, password, username, year)
VALUES ('ivanov', '$2a$12$ZLij0jYEBVbWiiLndaoIe.06eB1mVoUpcd.ue1bf9urymRUjvcQsG', 'ivan', 2000);
INSERT INTO users_roles
SET user_id=(SELECT id FROM users WHERE username = 'ivan'),
    role_id = (SELECT id FROM roles WHERE name = 'ADMIN');
INSERT INTO users_roles
SET user_id=(SELECT id FROM users WHERE username = 'ivan'),
    role_id = (SELECT id FROM roles WHERE name = 'USER');