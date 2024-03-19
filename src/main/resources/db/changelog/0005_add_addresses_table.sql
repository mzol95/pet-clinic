CREATE TABLE addresses (
  id BIGINT PRIMARY KEY,
  street VARCHAR(255),
  city VARCHAR(255),
  postal_code VARCHAR(255)
);


ALTER TABLE clients ADD COLUMN address_id BIGINT;
ALTER TABLE clients ADD FOREIGN KEY (address_id) REFERENCES addresses(id);
