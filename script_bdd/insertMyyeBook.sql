-- Insertion dans la table Auteur
INSERT INTO Auteur (aut_nom, aut_prenom, aut_photo)
VALUES ('Hugo', 'Victor', '/hugo.jpg'),
       ('Dumas', 'Alexandre', '/dumas.jpg'),
       ('Zola', 'Emile', '/zola.jpg'),
       ('Camus', 'Albert', '/camus.jpg'),
       ('Proust', 'Marcel', '/proust.jpg'),
       ('Sartre', 'Jean-Paul', '/sartre.jpg'),
       ('Verne', 'Jules', '/verne.jpg'),
       ('Flaubert', 'Gustave', '/flaubert.jpg'),
       ('Balzac', 'Honoré', '/balzac.jpg'),
       ('Molière', 'Jean-Baptiste', '/moliere.jpg');

-- Insertion dans la table Categorie
INSERT INTO Categorie (cat_nom)
VALUES ('Roman'),
       ('Science-fiction'),
       ('Théâtre'),
       ('Philosophie'),
       ('Histoire'),
       ('Poésie'),
       ('Fantastique'),
       ('Biographie'),
       ('Policier'),
       ('Humour');

-- Insertion dans la table Compte
INSERT INTO Compte (cpt_login, cpt_mdp, cpt_role)
VALUES ('lib1', 'password1M!', 'ROLE_LIBRAIRE'),
       ('lib2', 'password2M!', 'ROLE_LIBRAIRE'),
       ('cli1', 'password3M!', 'ROLE_CLIENT'),
       ('cli2', 'password4M!', 'ROLE_CLIENT'),
       ('cli3', 'password5M!', 'ROLE_CLIENT'),
       ('cli4', 'password6M!', 'ROLE_CLIENT'),
       ('cli5', 'password7M!', 'ROLE_CLIENT'),
       ('cli6', 'password8M!', 'ROLE_CLIENT'),
       ('cli7', 'password9M!', 'ROLE_CLIENT'),
       ('cli8', 'password10M!', 'ROLE_CLIENT');
;


-- Insertion dans la table Libraire
INSERT INTO Libraire (lib_nom, lib_prenom, cpt_id)
VALUES ('Durand', 'Paul', 1),
       ('Martin', 'Anne', 2);

-- Insertion dans la table Client
INSERT INTO Client (cli_nom, cli_prenom, cli_email, cli_adresse, cli_ville, cli_code_postale, cpt_id)
VALUES ('Dupont', 'Jean', 'jean.dupont@example.com', '10 Rue de Paris', 'Paris', '75001', 3),
       ('Leclerc', 'Marie', 'marie.leclerc@example.com', '15 Avenue des Champs', 'Lyon', '69001', 4),
       ('Petit', 'Luc', 'luc.petit@example.com', '25 Boulevard Haussmann', 'Marseille', '13001', 5),
       ('Morel', 'Sophie', 'sophie.morel@example.com', '8 Rue de Lille', 'Bordeaux', '33000', 6),
       ('Simon', 'Claire', 'claire.simon@example.com', '12 Place Bellecour', 'Nice', '06000', 7),
       ('Roux', 'Jacques', 'jacques.roux@example.com', '5 Rue du Faubourg', 'Toulouse', '31000', 8),
       ('Bernard', 'Julie', 'julie.bernard@example.com', '30 Rue de Rivoli', 'Nantes', '44000', 9),
       ('Blanc', 'Thomas', 'thomas.blanc@example.com', '50 Rue du Bac', 'Strasbourg', '67000', 10);

-- Insertion dans la table Livre
INSERT INTO Livre (liv_titre, liv_resume, liv_photo, liv_en_avant, aut_id, cat_id)
VALUES ('Les Misérables', 'Un roman historique...', '/les_miserables.jpg', true, 1, 1),
       ('Le Comte de Monte-Cristo', 'Un classique...', '/monte_cristo.jpg', false, 2, 1),
       ('Germinal', 'Une fresque sociale...', '/germinal.jpg', true, 3, 1),
       ('L’Étranger', 'Un roman philosophique...', '/etranger.jpg', false, 4, 4),
       ('À la recherche du temps perdu', 'Un chef-d’œuvre...', '/temps_perdu.jpg', false, 5, 1),
       ('Les Mouches', 'Une pièce de théâtre...', '/les_mouches.jpg', true, 6, 3),
       ('Vingt mille lieues sous les mers', 'Un roman d’aventure...', '/20mille.jpg', true, 7, 2),
       ('Madame Bovary', 'Une œuvre réaliste...', '/madame_bovary.jpg', false, 8, 1),
       ('La Comédie humaine', 'Un cycle de romans...', '/comedie_humaine.jpg', false, 9, 1),
       ('Le Malade imaginaire', 'Une comédie...', '/malade_imaginaire.jpg', true, 10, 3);

-- Insertion dans la table Exemplaire
INSERT INTO Exemplaire (liv_id)
VALUES (1),
       (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7),
       (8),
       (9);

-- Insertion dans la table Reservation
INSERT INTO Reservation (res_date, liv_id, cli_id)
VALUES ('2025-01-10 10:00:00', 1, 1),
       ('2025-01-11 12:00:00', 2, 2),
       ('2025-01-12 15:00:00', 3, 3),
       ('2025-01-13 14:30:00', 4, 4);

-- Insertion dans la table Emprunter
INSERT INTO Emprunter (res_id, emp_date_emprunt, emp_date_retour, cli_id, lib_id, exe_id)
VALUES (1, '2025-01-10 11:00:00', '2025-01-20 11:00:00', 1, 1, 1),
       (2, '2025-01-11 13:00:00', '2025-01-21 13:00:00', 2, 2, 2),
       (3, '2025-01-12 16:00:00', NULL, 3, 1, 3),
       (4, '2025-01-13 15:30:00', '2025-01-23 15:30:00', 4, 2, 4);