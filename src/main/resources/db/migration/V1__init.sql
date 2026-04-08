CREATE TABLE client (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        city VARCHAR(255) NOT NULL,
                        number BIGINT NOT NULL
);

CREATE TABLE vehicle (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         registration_number VARCHAR(255) NOT NULL UNIQUE,
                         type VARCHAR(255) NOT NULL,
                         capacity INT NOT NULL,
                         vehicle_status VARCHAR(50) NOT NULL
);

CREATE TABLE driver (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        number BIGINT NOT NULL,
                        licence_type VARCHAR(255) NOT NULL,
                        available BOOLEAN NOT NULL
);

CREATE TABLE delivery (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          delivery_date DATE NOT NULL,
                          start_address VARCHAR(255) NOT NULL,
                          end_address VARCHAR(255) NOT NULL,
                          delivery_status VARCHAR(50) NOT NULL,
                          client_id INT,
                          driver_id INT,
                          vehicle_id INT
);