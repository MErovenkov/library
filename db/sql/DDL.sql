CREATE DATABASE  library;

CREATE TABLE IF NOT EXISTS author(
id_author int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
surname varchar(255) NOT NULL,
name varchar(255) NOT NULL,
patronymic varchar(255) NOT NULL,

UNIQUE(surname, name, patronymic)
);

CREATE TABLE IF NOT EXISTS genre(
id_genre int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
name varchar(255) NOT NULL,

UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS author_genre(
id_author int NOT NULL,
id_genre int NOT NULL,

UNIQUE (id_author, id_genre),
FOREIGN KEY(id_author) REFERENCES author(id_author),
FOREIGN KEY(id_genre) REFERENCES genre(id_genre)
);

CREATE TABLE IF NOT EXISTS publisher(
id_publisher int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
name varchar(255) NOT NULL,
address varchar(255),

UNIQUE(name, address)
);

CREATE TABLE IF NOT EXISTS book(
id_book int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
name varchar(255) NOT NULL,
id_author int NOT NULL,
id_genre int NOT NULL,
id_publisher int NOT NULL,
short_specification varchar(255),
number_pages int NOT NULL,
in_stock boolean NOT NULL,

UNIQUE(name),

FOREIGN KEY(id_author) REFERENCES author(id_author),
FOREIGN KEY(id_genre) REFERENCES genre(id_genre),
FOREIGN KEY(id_publisher) REFERENCES publisher(id_publisher)
);

CREATE TABLE IF NOT EXISTS authority(
id_authority int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
name varchar(255) NOT NULL,

UNIQUE(name)
);

INSERT INTO authority(id_authority, name) VALUES (1, 'ADMIN');
INSERT INTO authority(id_authority, name) VALUES (2, 'USER');
INSERT INTO authority(id_authority, name) VALUES (3, 'VISITOR');

CREATE TABLE IF NOT EXISTS users(
id_user int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
username varchar(255) NOT NULL,
password varchar(255) NOT NULL,

UNIQUE(username)
);

CREATE TABLE IF NOT EXISTS users_authority(
id_user int NOT NULL,
id_authority int NOT NULL,

UNIQUE(id_user, id_authority),
FOREIGN KEY(id_user) REFERENCES users(id_user),
FOREIGN KEY(id_authority) REFERENCES authority(id_authority)
);

CREATE TABLE IF NOT EXISTS reader_card(
id_reader_card int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
surname varchar(255) NOT NULL,
name varchar(255) NOT NULL,
patronymic varchar(255),
number_phone int NOT NULL,
email varchar(255) UNIQUE,
penalty int NOT NULL,
max_books_taken int NOT NULL,

FOREIGN KEY(id_reader_card) REFERENCES users(id_user)
);

CREATE TABLE IF NOT EXISTS entry(
id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
id_reader_card int NOT NULL,
id_book int NOT NULL,
take_date date NOT NULL,
return_date_planned date NOT NULL,
return_date date NOT NULL,
entry_status varchar(255) NOT NULL,

FOREIGN KEY(id_reader_card) REFERENCES reader_card(id_reader_card),
FOREIGN KEY(id_book) REFERENCES book(id_book)
);