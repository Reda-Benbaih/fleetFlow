CREATE TABLE user(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(64) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(64) NOT NULL,
    user_roles VARCHAR(10) NOT NULL
);

CREATE TABLE client (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    number BIGINT NOT NULL,
    CONSTRAINT fk_client_user FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE driver (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    number BIGINT NOT NULL,
    licence_type VARCHAR(255) NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_driver_user FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE vehicle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    registration_number VARCHAR(255) NOT NULL UNIQUE,
    type VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    vehicle_status VARCHAR(50) NOT NULL DEFAULT 'AVAILABLE',
    CONSTRAINT chk_vehicle_capacity_positive CHECK (capacity > 0)
);

CREATE TABLE delivery (
    id INT AUTO_INCREMENT PRIMARY KEY,
    delivery_date DATE NOT NULL,
    start_address VARCHAR(255) NOT NULL,
    end_address VARCHAR(255) NOT NULL,
    delivery_status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    client_id INT,
    driver_id INT,
    vehicle_id INT,
    CONSTRAINT fk_delivery_client FOREIGN KEY (client_id) REFERENCES client(id),
    CONSTRAINT fk_delivery_driver FOREIGN KEY (driver_id) REFERENCES driver(id),
    CONSTRAINT fk_delivery_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);