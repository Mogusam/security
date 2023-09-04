--DELETE from users;
INSERT INTO users (id, username, password, enabled)
values
(1, 'user', 'password', true),
(2, 'admin', 'password', true);

--DELETE from authorities;
INSERT INTO authorities (username, authority)
values ('user', 'ROLE_USER'),

 ('admin', 'ROLE_USER'),

 ('admin', 'ROLE_ADMIN');