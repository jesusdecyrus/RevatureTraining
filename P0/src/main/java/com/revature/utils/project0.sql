DROP TABLE IF EXISTS trainers
DROP TABLE IF EXISTS pokemon

-- Trainers Table
CREATE TABLE trainers(
 trainerID serial PRIMARY KEY,
 firstName TEXT NOT NULL,
 lastName TEXT NOT NULL,
 username TEXT UNIQUE,
 password TEXT NOT NULL
);
INSERT INTO trainers(firstName, lastName, username, password)
VALUES  ('Cyrus', 'De Jesus', 'cdejesus', 'password'),
	    ('Benjamin', 'Petruzziello', 'bpetruzziello', 'password');

-- Pokemon Table
CREATE TABLE pokemon(
 pokemonID serial PRIMARY KEY,
 name TEXT NOT NULL,
 trainerID INT,
 type TEXT NOT NULL,
 level INT NOT NULL CHECK (level >= 1 AND level <= 100),
 gender CHAR(1) NOT NULL CHECK (gender IN ('M', 'F')),
 isShiny BOOLEAN NOT NULL,
 FOREIGN KEY (trainerID) REFERENCES trainers(trainerID) ON DELETE CASCADE
);
INSERT INTO pokemon(name, trainerID, type, level, gender, isShiny)
VALUES	('Bulbasaur', 1, 'Grass/Poison', 100, 'M', FALSE),
		('Gardevoir', 1, 'Psychic/Fairy', 50, 'F', TRUE),
		('Trubbish', 2, 'Poison', 1, 'M', FALSE);