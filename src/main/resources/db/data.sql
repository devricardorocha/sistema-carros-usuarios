DROP TABLE IF EXISTS carro;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
	creation DATE NOT NULL,
	lastLogin DATE
);

CREATE TABLE carro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `year` INT NOT NULL,
    licensePlate VARCHAR(20) NOT NULL,
    model VARCHAR(255) NOT NULL,
    color VARCHAR(50) NOT NULL,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

INSERT INTO usuario (firstName, lastName, email, birthday, login, password, phone, creation, lastLogin) VALUES
('Alice', 'Silva', 'alice.silva@example.com', '1990-01-15', 'alice_silva', 'senha123', '123456789', CURRENT_DATE, CURRENT_DATE),
('Bruno', 'Costa', 'bruno.costa@example.com', '1985-05-23', 'bruno_costa', 'senha123', '987654321', CURRENT_DATE, CURRENT_DATE),
('Carla', 'Souza', 'carla.souza@example.com', '1992-09-30', 'carla_souza', 'senha123', '456789123', CURRENT_DATE, CURRENT_DATE);

INSERT INTO carro (`year`, licensePlate, model, color, usuario_id) VALUES
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
