CREATE TABLE addresses
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    street      VARCHAR(255),
    city        VARCHAR(255),
    postal_code VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE clients
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255),
    surname    VARCHAR(255),
    phone      VARCHAR(255),
    address_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (address_id) REFERENCES addresses (id)
);

CREATE TABLE pets
(
    id            BIGINT NOT NULL AUTO_INCREMENT,
    name          VARCHAR(255),
    date_of_birth DATETIME,
    gender        TINYINT,
    client_id     BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES clients (id)
);


CREATE TABLE work_days
(
    id               BIGINT NOT NULL AUTO_INCREMENT,
    date_of_work     DATETIME,
    start_of_work    TIME,
    end_of_work      TIME,
    work_schedule_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE work_schedules
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE doctors
(
    id               BIGINT NOT NULL AUTO_INCREMENT,
    name             VARCHAR(255),
    surname          VARCHAR(255),
    work_schedule_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (work_schedule_id) REFERENCES work_schedules (id)
);

CREATE TABLE appointments
(
    id               BIGINT NOT NULL AUTO_INCREMENT,
    appointment_date DATETIME,
    finished         BOOLEAN,
    client_id        BIGINT,
    pet_id           BIGINT,
    doctor_id        BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (pet_id) REFERENCES pets (id),
    FOREIGN KEY (doctor_id) REFERENCES doctors (id)
);






