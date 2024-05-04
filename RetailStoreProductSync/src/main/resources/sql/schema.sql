CREATE DATABASE retailStore;

CREATE TABLE products (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    price DECIMAL(10, 2),
    description TEXT,
    category VARCHAR(255),
    image VARCHAR(255),
    rate DECIMAL(3, 1),
    count INT,
    PRIMARY KEY (id)
);