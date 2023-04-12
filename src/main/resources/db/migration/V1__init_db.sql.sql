CREATE TABLE client(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR
);
ALTER TABLE client ADD CONSTRAINT check_client_name CHECK(LENGTH(name) >= 3 AND LENGTH(name) <= 200);

CREATE TABLE planet(
	id VARCHAR PRIMARY KEY,
	name VARCHAR
);
ALTER TABLE planet ADD CONSTRAINT check_planet_id CHECK(id REGEXP '^[A-Z0-9]+$');
ALTER TABLE planet ADD CONSTRAINT check_planet_name CHECK(LENGTH(name) >= 1 AND LENGTH(name) <= 500);

CREATE TABLE ticket (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  client_id INTEGER NOT NULL,
  from_planet_id VARCHAR NOT NULL,
  to_planet_id VARCHAR NOT NULL,
  FOREIGN KEY (client_id) REFERENCES client(id),
  FOREIGN KEY (from_planet_id) REFERENCES planet(id),
  FOREIGN KEY (to_planet_id) REFERENCES planet(id)
);