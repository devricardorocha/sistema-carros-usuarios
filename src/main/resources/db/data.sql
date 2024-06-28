DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
	created DATE NOT NULL,
	lastLogin DATETIME,
    CONSTRAINT unique_login UNIQUE (login),
    CONSTRAINT unique_email UNIQUE (email)
);

CREATE TABLE cars (
    id INT AUTO_INCREMENT PRIMARY KEY,
    _year INT NOT NULL,
    licensePlate VARCHAR(20) NOT NULL,
    model VARCHAR(255) NOT NULL,
    color VARCHAR(50) NOT NULL,
    users_id INT,
    CONSTRAINT unique_license_plate UNIQUE (licensePlate),
    FOREIGN KEY (users_id) REFERENCES users(id)
);

INSERT INTO users (firstName, lastName, email, birthday, login, password, phone, created, lastLogin) VALUES
('Alice', 'Silva', 'alice.silva@example.com', '1990-01-15', 'alice_silva', '$2a$10$ez3tz3lKvMBaCAAbz6CGJ.r.c59fZvPHb2OawdLkoCdNo9Dx0tx7K', '6920777463', CURRENT_DATE, CURRENT_TIMESTAMP),
('Bruno', 'Costa', 'bruno.costa@example.com', '1985-05-23', 'bruno_costa', '$2a$10$ez3tz3lKvMBaCAAbz6CGJ.r.c59fZvPHb2OawdLkoCdNo9Dx0tx7K', '69937286121', CURRENT_DATE, CURRENT_TIMESTAMP),
('Carla', 'Souza', 'carla.souza@example.com', '1992-09-30', 'carla_souza', '$2a$10$ez3tz3lKvMBaCAAbz6CGJ.r.c59fZvPHb2OawdLkoCdNo9Dx0tx7K', '8636874736', CURRENT_DATE, CURRENT_TIMESTAMP);

INSERT INTO cars (_year, licensePlate, model, color, users_id) VALUES
(2020, 'ABC-1234', 'Corolla', 'Preto', 1),
(2019, 'DEF-5678', 'Civic', 'Branco', 2),
(2018, 'GHI-9101', 'Focus', 'Cinza', 3),
(2017, 'JKL-1213', 'Cruze', 'Azul', 1),
(2021, 'MNO-1415', 'Golf', 'Vermelho', 2),
(2020, 'PQR-1617', 'Elantra', 'Preto', 3),
(2018, 'STU-1819', 'Sentra', 'Branco', 1),
(2019, 'VWX-2021', 'Cerato', 'Cinza', 2),
(2017, 'YZA-2223', 'Megane', 'Azul', 3),
(2021, 'BCD-2425', '308', 'Vermelho', 1);
