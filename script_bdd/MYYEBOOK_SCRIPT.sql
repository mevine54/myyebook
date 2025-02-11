

DROP DATABASE IF EXISTS myyebook;
CREATE DATABASE myyebook;
USE myyebook;

CREATE TABLE Auteur(
   aut_id INT AUTO_INCREMENT,
   aut_nom VARCHAR(50) NOT NULL,
   aut_prenom VARCHAR(50),
   aut_photo VARCHAR(250) NOT NULL,
   CONSTRAINT PK_Auteur PRIMARY KEY(aut_id)
);

CREATE TABLE Compte(
   cpt_id INT AUTO_INCREMENT,
   cpt_login VARCHAR(20) NOT NULL,
   cpt_mdp VARCHAR(300) NOT NULL,
   cpt_role ENUM( 'ROLE_CLIENT','ROLE_LIBRAIRE','ROLE_LIBRAIRE_ATTENTE') NOT NULL,
   cpt_date DATE NOT NULL  DEFAULT (CURRENT_DATE) ,
   CONSTRAINT PK_Compte PRIMARY KEY(cpt_id),
   CONSTRAINT AK_Compte UNIQUE(cpt_login)
);

CREATE TABLE Categorie(
   cat_id INT AUTO_INCREMENT,
   cat_nom VARCHAR(100) NOT NULL,
   CONSTRAINT PK_Categorie PRIMARY KEY(cat_id),
   CONSTRAINT AK_Categorie UNIQUE(cat_nom)
);

CREATE TABLE Client(
   cli_id INT AUTO_INCREMENT,
   cli_nom VARCHAR(50) NOT NULL,
   cli_prenom VARCHAR(50) NOT NULL,
   cli_email VARCHAR(50) NOT NULL,
   cli_adresse VARCHAR(250) NOT NULL,
   cli_ville VARCHAR(50) NOT NULL,
   cli_code_postale VARCHAR(8),
   cpt_id INT NOT NULL,
   CONSTRAINT PK_Client PRIMARY KEY(cli_id),
   CONSTRAINT AK_Client UNIQUE(cpt_id),
   CONSTRAINT FK_Client_Compte FOREIGN KEY(cpt_id) REFERENCES Compte(cpt_id)
);

CREATE TABLE Libraire(
   lib_id INT AUTO_INCREMENT,
   lib_nom VARCHAR(50) NOT NULL,
   lib_prenom VARCHAR(50) NOT NULL,
   lib_est_approuve BOOLEAN NOT NULL,
   cpt_id INT NOT NULL,
   CONSTRAINT PK_Libraire PRIMARY KEY(lib_id),
   CONSTRAINT AK_Libraire UNIQUE(cpt_id),
   CONSTRAINT FK_Libraire_Compte FOREIGN KEY(cpt_id) REFERENCES Compte(cpt_id)
);

CREATE TABLE Livre(
   liv_id INT AUTO_INCREMENT,
   liv_titre VARCHAR(100) NOT NULL,
   liv_resume VARCHAR(2000) NOT NULL,
   liv_photo VARCHAR(250) NOT NULL,
   liv_en_avant BOOLEAN NOT NULL DEFAULT false,
   quantite INT NOT NULL,
   aut_id INT NOT NULL,
   cat_id INT NOT NULL,
   CONSTRAINT PK_Livre PRIMARY KEY(liv_id),
   CONSTRAINT FK_Livre_Auteur FOREIGN KEY(aut_id) REFERENCES Auteur(aut_id),
   CONSTRAINT FK_Livre_Categorie FOREIGN KEY(cat_id) REFERENCES Categorie(cat_id)
);

CREATE TABLE Emprunter(
   cli_id INT,
   lib_id INT,
   liv_id INT,
   emp_res_date DATETIME NOT NULL,
   emp_date_emprunt DATETIME,
   emp_date_retour DATETIME,
   CONSTRAINT PK_Emprunter PRIMARY KEY(cli_id, lib_id, liv_id),
   CONSTRAINT FK_Emprunter_Client FOREIGN KEY(cli_id) REFERENCES Client(cli_id),
   CONSTRAINT FK_Emprunter_Libraire FOREIGN KEY(lib_id) REFERENCES Libraire(lib_id),
   CONSTRAINT FK_Emprunter_Livre FOREIGN KEY(liv_id) REFERENCES Livre(liv_id)
);

DELIMITER $$;
CREATE TRIGGER validate_compte
BEFORE INSERT ON Client
FOR EACH ROW
BEGIN
    DECLARE count_libraire INT;
    DECLARE count_client INT;

    -- Vérifie si le compte est déjà utilisé par un libraire
    SELECT
        COUNT(*) INTO count_libraire
    FROM
        Libraire
    WHERE
        cpt_id = NEW.cpt_id;

    -- Si le compte est utilisé par un libraire, empêche l'insertion
    IF count_libraire > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ce compte est déjà utilisé par un libraire.';
    END IF;

    -- Vérifie si le compte est déjà utilisé par un client
    SELECT
        COUNT(*) INTO count_client
    FROM
        Client
    WHERE
        cpt_id = NEW.cpt_id;

    -- Si le compte est utilisé par un client, empêche l'insertion
    IF count_client > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ce compte est déjà utilisé par un client.';
    END IF;
END $$
DELIMITER ;

DELIMITER $$;
CREATE TRIGGER validate_libraire
BEFORE INSERT ON Libraire
FOR EACH ROW
BEGIN
    DECLARE count_libraire INT;
    DECLARE count_client INT;
    -- Vérifie si le compte est déjà utilisé par un client
    SELECT
        COUNT(*) INTO count_client
    FROM
        Client
    WHERE
        cpt_id = NEW.cpt_id;
    -- Si le compte est utilisé par un client, empêche l'insertion
    IF count_client > 0 THEN SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Ce compte est déjà utilisé par un client.';
    END IF;
    -- Vérifie si le compte est déjà utilisé par un libraire
    SELECT
        COUNT(*) INTO count_libraire
    FROM
        Libraire
    WHERE
        cpt_id = NEW.cpt_id;
    -- Si le compte est utilisé par un libraire, empêche l'insertion
    IF
        count_libraire > 0
    THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ce compte est déjà utilisé par un libraire.';
    END IF;
END $$
DELIMITER ;


DROP table Emprunter;

CREATE TABLE Emprunter(
   emp_id INT AUTO_INCREMENT PRIMARY KEY,
   emp_res_date DATETIME NOT NULL,
   emp_date_emprunt DATETIME ,
   emp_date_retour DATETIME,
   cli_id INT NOT NULL,
   lib_id INT NOT NULL,
   liv_id INT NOT NULL,
   FOREIGN KEY(cli_id) REFERENCES Client(cli_id),
   FOREIGN KEY(lib_id) REFERENCES Libraire(lib_id),
   FOREIGN KEY(liv_id) REFERENCES Livre(liv_id)
);

DELIMITER //

-- Trigger pour vérifier que la date d'emprunt est après la date de réservation
CREATE TRIGGER before_insert_emprunt
BEFORE INSERT ON Emprunter
FOR EACH ROW
BEGIN
    IF NEW.emp_date_emprunt IS NOT NULL AND NEW.emp_date_emprunt <= NEW.emp_res_date THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La date d\'emprunt doit être après la date de réservation.';
    END IF;
END;
//

-- Trigger pour vérifier que la date de retour est après la date d'emprunt
CREATE TRIGGER before_insert_retour
BEFORE INSERT ON Emprunter
FOR EACH ROW
BEGIN
    IF NEW.emp_date_retour IS NOT NULL AND NEW.emp_date_retour <= NEW.emp_date_emprunt THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La date de retour doit être après la date d\'emprunt.';
    END IF;
END;
//

-- Trigger pour vérifier que la date de retour ne peut être fixée que si la date d'emprunt est fixée
CREATE TRIGGER before_update_retour
BEFORE UPDATE ON Emprunter
FOR EACH ROW
BEGIN
    IF NEW.emp_date_retour IS NOT NULL AND NEW.emp_date_emprunt IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La date de retour ne peut être fixée que si la date d\'emprunt est fixée.';
    END IF;
END;
//

DELIMITER ;

DELIMITER //

CREATE TRIGGER before_update_emprunt
BEFORE UPDATE ON Emprunter
FOR EACH ROW
BEGIN
    DECLARE livre_quantite INT;

    -- Récupérer la quantité du livre
    SELECT quantite INTO livre_quantite
    FROM Livre
    WHERE liv_id = NEW.liv_id;

    -- Vérifier si la quantité est zéro et si la date d'emprunt est fixée
    IF livre_quantite = 0 AND NEW.emp_date_emprunt IS NOT NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La date d\'emprunt ne peut pas être fixée car la quantité du livre est zéro.';
    END IF;
END;
//

DELIMITER ;

ALTER TABLE Libraire
ADD COLUMN quantite INT NOT NULL DEFAULT 0,
ADD CONSTRAINT CHK_Quantite CHECK (quantite >= 0);
