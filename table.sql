CREATE TABLE `students` (
    `index` INTEGER NOT NULL,
    `frecventa` INTEGER NOT NULL,
    `nume` VARCHAR(255) NOT NULL,
	`cnp` DOUBLE NOT NULL,
	`notaBac` FLOAT NOT NULL,
	`oras` VARCHAR(255) NOT NULL,
	`idLegit` INTEGER  NOT NULL,
	PRIMARY KEY (`cnp`),
);
CREATE TABLE `facultati` (
    `index` INTEGER NOT NULL,
    `nume` VARCHAR(255) NOT NULL,
    `oras` VARCHAR(255) NOT NULL,
    `decan` VARCHAR(255) NOT NULL,
    `procentajBac` INTEGER NOT NULL,
    PRIMARY KEY (`index`)
);
CREATE TABLE `frecventa` (
    `index` INTEGER NOT NULL,
    `locatie` VARCHAR(255) NOT NULL,
    `locuri` INTEGER NOT NULL,
    `nrExamene` INTEGER NOT NULL
);
CREATE TABLE `distanta` (
    `index` INTEGER NOT NULL,
    `locatie` VARCHAR(255) NOT NULL,
    `locuri` INTEGER NOT NULL,
    `luna` INTEGER NOT NULL,
    `zi` INTEGER NOT NULL,
    `numeHr` VARCHAR(255) NOT NULL
);
CREATE TABLE `examen` (
    `index` INTEGER NOT NULL,
    `materie` VARCHAR(255) NOT NULL,
    `luna` INTEGER NOT NULL,
    `zi` INTEGER NOT NULL,
    `tipExercitii` INTEGER NOT NULL
);