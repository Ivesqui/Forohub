CREATE TABLE responses(
    id BIGINT NOT NULL AUTO_INCREMENT,
    response_content VARCHAR(2000) NOT NULL,
    topic_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (topic_id) REFERENCES topics(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);