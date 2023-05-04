DROP SCHEMA IF EXISTS vakcinisanje;
CREATE SCHEMA vakcinisanje DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE vakcinisanje;

CREATE TABLE vakcine (
	id BIGINT AUTO_INCREMENT,
	naziv VARCHAR(150) UNIQUE NOT NULL,
	tip VARCHAR(150) NOT NULL,
    temperturaCuvanja INT(30) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE prijave (
	id BIGINT AUTO_INCREMENT,
	jmbg VARCHAR (70) NOT NULL,
    imeIPrezime VARCHAR (130) NOT NULL,
    vakcina BIGINT NOT NULL,
    datum DATETIME NOT NULL,
	PRIMARY KEY(id), 
    FOREIGN KEY(vakcina) REFERENCES vakcine(id)
);


# vakcine: id, naziv, tip, temperatura čuvanja
INSERT INTO vakcine (id,naziv,tip,temperturaCuvanja) VALUES (1, 'Pfizer-BioNTech', 'RNK vakcina', -70);
INSERT INTO vakcine (id,naziv,tip,temperturaCuvanja) VALUES (2, 'Sputnik V', 'vakcina sa viralnim vektorom', 8);
INSERT INTO vakcine (id,naziv,tip,temperturaCuvanja) VALUES (3, 'Sinopharm', 'inaktivisana vakcina', 8);
INSERT INTO vakcine (id,naziv,tip,temperturaCuvanja) VALUES (4, 'AstraZeneca', 'vakcina sa viralnim vektorom', 8);
INSERT INTO vakcine (id,naziv,tip,temperturaCuvanja) VALUES (5, 'Moderna', 'RNK vakcina', -20);

# prijave: jmbg, ime i prezime, vakcina, datum
INSERT INTO prijave (jmbg,imeIPrezime,vakcina, datum) VALUES ('1111111111111', 'Aaa Aaa', 1, '2021-01-01');
INSERT INTO prijave (jmbg,imeIPrezime,vakcina, datum) VALUES ('2222222222222', 'Bbb Bbb', 1, '2021-01-15');
INSERT INTO prijave (jmbg,imeIPrezime,vakcina, datum) VALUES ('3333333333333', 'Ccc Ccc', 2, '2021-02-01');
INSERT INTO prijave (jmbg,imeIPrezime,vakcina, datum) VALUES ('4444444444444', 'Ddd Ddd', 3, '2021-02-15');
INSERT INTO prijave (jmbg,imeIPrezime,vakcina, datum) VALUES ('5555555555555', 'Eee Eee', 2, '2021-03-01');