CREATE TABLE User (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);

CREATE TABLE country (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE client (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL,
  email VARCHAR(250) NULL,
  address VARCHAR(250) NOT NULL,
  user_id INT NOT NULL,
  country_id INT NOT NULL
);

ALTER TABLE client ADD CONSTRAINT fk__client__user_id FOREIGN KEY (user_id) REFERENCES user(id);
ALTER TABLE client ADD CONSTRAINT fk__client__country_id FOREIGN KEY (country_id) REFERENCES country(id);

CREATE INDEX ix__client__user_id ON client(user_id);
CREATE INDEX ix__client__country_id ON client(country_id);

INSERT INTO country (name) VALUES
  ('Estonia'),
  ('Latvia'),
  ('Lithuania'),
  ('Finland'),
  ('Finland');

INSERT INTO User (username, password) VALUES
  ('Jaan', '$2a$10$OevM6NPsALIKiQvQQTeCj.sedKdnUJmRiLXTc/UV/QGGtaC5IWyZi'),
  ('Toomas', '$2a$10$pUJ21nBzPXzAM40Uxg816eukMNoZ35qsAwpUv1gIZjGLO8ZA1jrm6'),
  ('Peeter', '$2a$10$BuezJDzaAjeUn4u9.lTJCuWpsA3bCOFIep.GbSklMq3KFDeWbpJe.');