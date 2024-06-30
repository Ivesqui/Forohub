CREATE TABLE users(
    id bigint not null auto_increment,
    username varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(300) not null,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

