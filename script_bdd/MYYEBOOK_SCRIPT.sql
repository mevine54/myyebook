
DROP DATABASE IF EXISTS myyebook;
CREATE DATABASE myyebook;
USE myyebook;

CREATE TABLE Auteur(
                       aut_id INT AUTO_INCREMENT,
                       aut_nom VARCHAR(50) NOT NULL,
                       aut_prenom VARCHAR(50),
                       aut_photo VARCHAR(250) NOT NULL,
                       PRIMARY KEY(aut_id)
);

CREATE TABLE Compte(
                       cpt_id INT AUTO_INCREMENT,
                       cpt_login VARCHAR(20) NOT NULL,
                       cpt_mdp VARCHAR(300) NOT NULL,
                       cpt_date DATE NOT NULL DEFAULT (CURRENT_DATE) ,
                       cpt_role ENUM( 'ROLE_CLIENT','ROLE_LIBRAIRE','ROLE_LIBRAIRE_ATTENTE') NOT NULL ,
                       PRIMARY KEY(cpt_id),
                       UNIQUE(cpt_login)
);

CREATE TABLE Categorie(
                          cat_id INT AUTO_INCREMENT,
                          cat_nom VARCHAR(100) NOT NULL,
                          PRIMARY KEY(cat_id),
                          UNIQUE(cat_nom)
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
                       PRIMARY KEY(cli_id),
                       UNIQUE(cpt_id),
                       FOREIGN KEY(cpt_id) REFERENCES Compte(cpt_id)
);

CREATE TABLE Libraire(
                         lib_id INT AUTO_INCREMENT,
                         lib_nom VARCHAR(50) NOT NULL,
                         lib_prenom VARCHAR(50) NOT NULL,
                         lib_est_approuve BOOLEAN ,
                         cpt_id INT NOT NULL,
                         PRIMARY KEY(lib_id),
                         UNIQUE(cpt_id),
                         FOREIGN KEY(cpt_id) REFERENCES Compte(cpt_id)
);

CREATE TABLE Livre(
                      liv_id INT AUTO_INCREMENT,
                      liv_titre VARCHAR(100) NOT NULL,
                      liv_resume VARCHAR(2000) NOT NULL,
                      liv_photo VARCHAR(250) NOT NULL,
                      liv_en_avant BOOLEAN NOT NULL DEFAULT false,
                      aut_id INT NOT NULL,
                      cat_id INT NOT NULL,
                      PRIMARY KEY(liv_id),
                      FOREIGN KEY(aut_id) REFERENCES Auteur(aut_id),
                      FOREIGN KEY(cat_id) REFERENCES Categorie(cat_id)
);

CREATE TABLE Exemplaire(
                           exe_id INT AUTO_INCREMENT,
                           liv_id INT NOT NULL,
                           PRIMARY KEY(exe_id),
                           FOREIGN KEY(liv_id) REFERENCES Livre(liv_id)
);

CREATE TABLE Reservation(
                            res_id INT AUTO_INCREMENT,
                            res_date DATETIME,
                            liv_id INT NOT NULL,
                            cli_id INT NOT NULL,
                            PRIMARY KEY(res_id),
                            FOREIGN KEY(liv_id) REFERENCES Livre(liv_id),
                            FOREIGN KEY(cli_id) REFERENCES Client(cli_id)
);

CREATE TABLE Emprunter(
                          emp_id INT AUTO_INCREMENT PRIMARY KEY,
                          res_id INT,
                          emp_date_emprunt DATETIME NOT NULL,
                          emp_date_retour DATETIME,
                          cli_id INT NOT NULL,
                          lib_id INT NOT NULL,
                          exe_id INT NOT NULL,
                          FOREIGN KEY(res_id) REFERENCES Reservation(res_id),
                          FOREIGN KEY(cli_id) REFERENCES Client(cli_id),
                          FOREIGN KEY(lib_id) REFERENCES Libraire(lib_id),
                          FOREIGN KEY(exe_id) REFERENCES Exemplaire(exe_id)
);