CREATE TABLE roles(
    id BIGINT NOT NULL auto_increment,
    role VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_role(
    id BIGINT NOT NULL auto_increment,
    id_role BIGINT NOT NULL,
    id_user BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (id_user) REFERENCES users(id),
    FOREIGN KEY (id_role) REFERENCES roles(id)
);

INSERT INTO roles (id, role) VALUES (1, "ADMIN");
INSERT INTO roles (id, role) VALUES (2, "USER");
