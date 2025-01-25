CREATE TABLE User(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    role varchar(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UC_Email UNIQUE (email)
)