CREATE TABLE IF NOT EXISTS users
(
    id INT not null primary key,
    username varchar(50) not null ,
    password varchar(500) not null,
    enabled  boolean not null
);

CREATE TABLE IF NOT EXISTS authorities
(
    id INT not null,
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);

--CREATE UNIQUE INDEX IF NOT EXISTS  ix_auth_username on authorities (username, authority);