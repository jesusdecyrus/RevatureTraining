-- Trainers Table
CREATE TABLE trainers(
 trainer_id serial PRIMARY KEY,
 first_name TEXT NOT NULL,
 last_name TEXT NOT NULL,
 username TEXT UNIQUE,
 password TEXT NOT NULL
);
INSERT INTO trainers(first_name, last_name, username, password)
VALUES  ('Cyrus', 'De Jesus', 'cdejesus', 'password'),
	    ('Benjamin', 'Petruzziello', 'bpetruzziello', 'password');

-- Pokemon Table
CREATE TABLE pokemon(
 pokemon_id serial PRIMARY KEY,
 name TEXT UNIQUE,
 trainer_id INT NOT NULL,
 type TEXT NOT NULL,
 level INT NOT NULL,
 gender CHAR(1) NOT NULL,
 is_shiny BOOLEAN NOT NULL,
 FOREIGN KEY (trainer_id) REFERENCES trainers(trainer_id)
);
INSERT INTO pokemon(name, trainer_id, type, level, gender, is_shiny)
VALUES	('Bulbasaur', 1, 'Grass/Poison', 100, 'M', FALSE),
		('Gardevoir', 1, 'Psychic/Fairy', 50, 'F', TRUE),
		('Trubbish', 2, 'Poison', 1, 'M', FALSE);