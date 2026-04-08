ALTER TABLE delivery
    ADD CONSTRAINT fk_delivery_client
        FOREIGN KEY (client_id) REFERENCES client(id);

ALTER TABLE delivery
    ADD CONSTRAINT fk_delivery_driver
        FOREIGN KEY (driver_id) REFERENCES driver(id);

ALTER TABLE delivery
    ADD CONSTRAINT fk_delivery_vehicle
        FOREIGN KEY (vehicle_id) REFERENCES vehicle(id);

ALTER TABLE vehicle
    MODIFY vehicle_status VARCHAR(50) NOT NULL DEFAULT 'AVAILABLE';

ALTER TABLE driver
    MODIFY available BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE delivery
    MODIFY delivery_status VARCHAR(50) NOT NULL DEFAULT 'PENDING';

ALTER TABLE vehicle
    ADD CONSTRAINT chk_vehicle_capacity_positive CHECK (capacity > 0);