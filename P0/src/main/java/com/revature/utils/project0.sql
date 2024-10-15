-- TRAINERS TABLE
CREATE TABLE trainers(
 trainerID serial PRIMARY KEY,
 firstName TEXT NOT NULL,
 lastName TEXT NOT NULL,
 username TEXT UNIQUE,
 password TEXT NOT NULL
);
INSERT INTO trainers(firstName, lastName, username, password)
VALUES  ('Trainer1', 'LastOne', 'trainerOne', 'password'),
	    ('Trainer2', 'LastTwo', 'trainerTwo', 'password'),
	    ('Trainer3', 'LastThree', 'trainerThree', 'password'),
	    ('Cyrus', 'De Jesus', 'cdejesus', 'password'),
	    ('Benjamin', 'Petruzziello', 'bpetruzziello', 'password');

-- POKEMON TABLE
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
VALUES	('Bulbasaur', 1, 'Grass/Poison', 10, 'M', FALSE),
        ('Charmander', 2, 'Fire', 11, 'M', FALSE),
        ('Squirtle', 3, 'Water', 12, 'M', FALSE),
        ('Bulbasaur', 4, 'Grass/Poison', 100, 'M', FALSE),
		('Articuno', 4, 'Ice/Flying', 50, 'F', FALSE),
		('Lickitung', 5, 'Normal', 1, 'M', TRUE);

-- KANTO POKEDEX
CREATE TABLE kantoPokedex(
 pokedexNumber serial PRIMARY KEY,
 name VARCHAR(50) NOT NULL,
 type VARCHAR(50) NOT NULL
);
INSERT INTO kantoPokedex(pokedexNumber, name, type)
VALUES 	(1, 'Bulbasaur', 'Grass/Poison'),
		(2, 'Ivysaur', 'Grass/Poison'),
		(3, 'Venusaur', 'Grass/Poison'),
		(4, 'Charmander', 'Fire'),
		(5, 'Charmeleon', 'Fire'),
		(6, 'Charizard', 'Fire/Flying'),
		(7, 'Squirtle', 'Water'),
		(8, 'Wartortle', 'Water'),
		(9, 'Blastoise', 'Water'),
		(10, 'Caterpie', 'Bug'),
		(11, 'Metapod', 'Bug'),
		(12, 'Butterfree', 'Bug/Flying'),
		(13, 'Weedle', 'Bug/Poison'),
		(14, 'Kakuna', 'Bug/Poison'),
		(15, 'Beedrill', 'Bug/Poison'),
		(16, 'Pidgey', 'Normal/Flying'),
		(17, 'Pidgeotto', 'Normal/Flying'),
		(18, 'Pidgeot', 'Normal/Flying'),
		(19, 'Rattata', 'Normal'),
		(20, 'Raticate', 'Normal'),
		(21, 'Spearow', 'Normal/Flying'),
		(22, 'Fearow', 'Normal/Flying'),
		(23, 'Ekans', 'Poison'),
		(24, 'Arbok', 'Poison'),
		(25, 'Pikachu', 'Electric'),
		(26, 'Raichu', 'Electric'),
		(27, 'Sandshrew', 'Ground'),
		(28, 'Sandslash', 'Ground'),
		(29, 'Nidoran♀', 'Poison'),
		(30, 'Nidorina', 'Poison'),
		(31, 'Nidoqueen', 'Poison/Ground'),
		(32, 'Nidoran♂', 'Poison'),
		(33, 'Nidorino', 'Poison'),
		(34, 'Nidoking', 'Poison/Ground'),
		(35, 'Clefairy', 'Fairy'),
		(36, 'Clefable', 'Fairy'),
		(37, 'Vulpix', 'Fire'),
		(38, 'Ninetales', 'Fire'),
		(39, 'Jigglypuff', 'Normal/Fairy'),
		(40, 'Wigglytuff', 'Normal/Fairy'),
		(41, 'Zubat', 'Poison/Flying'),
		(42, 'Golbat', 'Poison/Flying'),
		(43, 'Oddish', 'Grass/Poison'),
		(44, 'Gloom', 'Grass/Poison'),
		(45, 'Vileplume', 'Grass/Poison'),
		(46, 'Paras', 'Bug/Grass'),
		(47, 'Parasect', 'Bug/Grass'),
		(48, 'Venonat', 'Bug/Poison'),
		(49, 'Venomoth', 'Bug/Poison'),
		(50, 'Diglett', 'Ground'),
		(51, 'Dugtrio', 'Ground'),
		(52, 'Meowth', 'Normal'),
		(53, 'Persian', 'Normal'),
		(54, 'Psyduck', 'Water'),
		(55, 'Golduck', 'Water'),
		(56, 'Mankey', 'Fighting'),
		(57, 'Primeape', 'Fighting'),
		(58, 'Growlithe', 'Fire'),
		(59, 'Arcanine', 'Fire'),
		(60, 'Poliwag', 'Water'),
		(61, 'Poliwhirl', 'Water'),
		(62, 'Poliwrath', 'Water/Fighting'),
		(63, 'Abra', 'Psychic'),
		(64, 'Kadabra', 'Psychic'),
		(65, 'Alakazam', 'Psychic'),
		(66, 'Machop', 'Fighting'),
		(67, 'Machoke', 'Fighting'),
		(68, 'Machamp', 'Fighting'),
		(69, 'Bellsprout', 'Grass/Poison'),
		(70, 'Weepinbell', 'Grass/Poison'),
		(71, 'Victreebel', 'Grass/Poison'),
		(72, 'Tentacool', 'Water/Poison'),
		(73, 'Tentacruel', 'Water/Poison'),
		(74, 'Geodude', 'Rock/Ground'),
		(75, 'Graveler', 'Rock/Ground'),
		(76, 'Golem', 'Rock/Ground'),
		(77, 'Ponyta', 'Fire'),
		(78, 'Rapidash', 'Fire'),
		(79, 'Slowpoke', 'Water/Psychic'),
		(80, 'Slowbro', 'Water/Psychic'),
		(81, 'Magnemite', 'Electric/Steel'),
		(82, 'Magneton', 'Electric/Steel'),
		(83, 'Farfetch''d', 'Normal/Flying'),
		(84, 'Doduo', 'Normal/Flying'),
		(85, 'Dodrio', 'Normal/Flying'),
		(86, 'Seel', 'Water'),
		(87, 'Dewgong', 'Water/Ice'),
		(88, 'Grimer', 'Poison'),
		(89, 'Muk', 'Poison'),
		(90, 'Shellder', 'Water'),
		(91, 'Cloyster', 'Water/Ice'),
		(92, 'Gastly', 'Ghost/Poison'),
		(93, 'Haunter', 'Ghost/Poison'),
		(94, 'Gengar', 'Ghost/Poison'),
		(95, 'Onix', 'Rock/Ground'),
		(96, 'Drowzee', 'Psychic'),
		(97, 'Hypno', 'Psychic'),
		(98, 'Krabby', 'Water'),
		(99, 'Kingler', 'Water'),
		(100, 'Voltorb', 'Electric'),
		(101, 'Electrode', 'Electric'),
		(102, 'Exeggcute', 'Grass/Psychic'),
		(103, 'Exeggutor', 'Grass/Psychic'),
		(104, 'Cubone', 'Ground'),
		(105, 'Marowak', 'Ground'),
		(106, 'Hitmonlee', 'Fighting'),
		(107, 'Hitmonchan', 'Fighting'),
		(108, 'Lickitung', 'Normal'),
		(109, 'Koffing', 'Poison'),
		(110, 'Weezing', 'Poison'),
		(111, 'Rhyhorn', 'Ground/Rock'),
		(112, 'Rhydon', 'Ground/Rock'),
		(113, 'Chansey', 'Normal'),
		(114, 'Tangela', 'Grass'),
		(115, 'Kangaskhan', 'Normal'),
		(116, 'Horsea', 'Water'),
		(117, 'Seadra', 'Water'),
		(118, 'Goldeen', 'Water'),
		(119, 'Seaking', 'Water'),
		(120, 'Staryu', 'Water'),
		(121, 'Starmie', 'Water/Psychic'),
		(122, 'Mr. Mime', 'Psychic/Fairy'),
		(123, 'Scyther', 'Bug/Flying'),
		(124, 'Jynx', 'Ice/Psychic'),
		(125, 'Electabuzz', 'Electric'),
		(126, 'Magmar', 'Fire'),
		(127, 'Pinsir', 'Bug'),
		(128, 'Tauros', 'Normal'),
		(129, 'Magikarp', 'Water'),
		(130, 'Gyarados', 'Water/Flying'),
		(131, 'Lapras', 'Water/Ice'),
		(132, 'Ditto', 'Normal'),
		(133, 'Eevee', 'Normal'),
		(134, 'Vaporeon', 'Water'),
		(135, 'Jolteon', 'Electric'),
		(136, 'Flareon', 'Fire'),
		(137, 'Porygon', 'Normal'),
		(138, 'Omanyte', 'Rock/Water'),
		(139, 'Omastar', 'Rock/Water'),
		(140, 'Kabuto', 'Rock/Water'),
		(141, 'Kabutops', 'Rock/Water'),
		(142, 'Aerodactyl', 'Rock/Flying'),
		(143, 'Snorlax', 'Normal'),
		(144, 'Articuno', 'Ice/Flying'),
		(145, 'Zapdos', 'Electric/Flying'),
		(146, 'Moltres', 'Fire/Flying'),
		(147, 'Dratini', 'Dragon'),
		(148, 'Dragonair', 'Dragon'),
		(149, 'Dragonite', 'Dragon/Flying'),
		(150, 'Mewtwo', 'Psychic'),
		(151, 'Mew', 'Psychic');