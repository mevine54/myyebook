-- Insertion dans la table Auteur
INSERT INTO Auteur (aut_nom, aut_prenom, aut_photo)
VALUES ('Hugo', 'Victor', '4313ade0-c1f5-45fc-9507-208b314cadcb.jpg'),
       ('Dumas', 'Alexandre', 'd930682e-62e0-4fa7-82f1-062e6605c111.jpg'),
       ('Zola', 'Emile', 'ab307055-5012-4b97-9073-03219ce15a30.jpg'),
       ('Camus', 'Albert', 'b855b449-d14a-421e-b843-a8432a4d084a.jpg'),
       ('Proust', 'Marcel', 'e6f24189-a94b-4892-b6c3-04226bfb40a3.jpg'),
       ('Sartre', 'Jean-Paul', '99b0d930-fffc-4ae3-a862-5943fa67bd01.jpg'),
       ('Verne', 'Jules', '07e16bbf-c989-444f-ac81-ae7c253c9fb0.jpg'),
       ('Flaubert', 'Gustave', '0495cf24-113c-4798-b9b2-79f9e8d316e5.jpg'),
       ('Balzac', 'Honoré', '5b2ad815-a1f5-4e38-82ef-6c574da4e005.jpg'),
       ('Molière', 'Jean-Baptiste', 'acd54ba5-a244-4944-894f-82be2078cc05.jpg');

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
       ('cli8', 'password10M!', 'ROLE_CLIENT'),
       ('lib3', 'password3M!', 'ROLE_LIBRAIRE_ATTENTE')
;


-- Insertion dans la table Libraire
INSERT INTO Libraire (lib_nom, lib_prenom,lib_est_approuve, cpt_id)
VALUES ('Durand', 'Paul',TRUE, 1),
       ('Martin', 'Anne',TRUE, 2),
       ('Marc', 'John',FALSE, 11)
;

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
VALUES ('Les Misérables', 'Un roman historique...', '1ad2a325-1021-4b83-bebb-db1932517e88.jpg', true, 1, 1),
       ('Le Comte de Monte-Cristo', 'Un classique...', '2c196cd4-bd08-4bda-ae39-4f716f9419ea.jpg', false, 2, 1),
       ('Germinal', 'Une fresque sociale...', '3ccdd34d-be42-4b21-b966-b5d925aa5d39.jpg', true, 3, 1),
       ('L’Étranger', 'Un roman philosophique...', '3d1c8775-e9d1-4533-a385-92723d319713.jpg', false, 4, 4),
       ('À la recherche du temps perdu', 'Un chef-d’œuvre...', '6e5ca510-2f7d-4693-bce2-75cafebe4887.jpg', false, 5, 1),
       ('Les Mouches', 'Une pièce de théâtre...', '7b26596d-cf18-4f96-a12f-5871025820ae.jpg', true, 6, 3),
       ('Vingt mille lieues sous les mers', 'Un roman d’aventure...', '55aae132-faf4-42e1-9c68-95f7a9cf4c63.jpg', true, 7, 2),
       ('Madame Bovary', 'Une œuvre réaliste...', '61d56c41-05f3-4e61-8241-e3a8c331af6c.jpg', false, 8, 1),
       ('La Comédie humaine', 'Un cycle de romans...', '283ae4b4-776a-48ab-8f61-6d17e018ed37.jpg', false, 9, 1),
       ('Le Malade imaginaire', 'Une comédie...', '83479cc1-1cd5-4b7b-aa56-5ba8b109136f.jpg', true, 10, 3),
       ('La gdfgdfge', 'Un cycle de romans...', '98704c2d-6347-468e-b662-25d27a82e67e.jpg', false, 9, 1),
       ('La za	z	az', 'Un cycle de romans...', '03479322-0fae-42d9-9de1-3aef1883d3fa.jpg', false, 9, 1),
       ('La piopio', 'Un cycle de romans...', 'af756329-11c4-4ca6-bfa3-fe4c52910474.jpg', false, 9, 1),
       ('La aze', 'Un cycle de romans...', 'b3085602-6b68-4bfe-9f96-d0c508c8d094.jpg', false, 9, 1),
       ('Lajhjghje', 'Un cycle de romans...', 'be798d86-d7bd-448d-b253-d5142ad30fe6.jpg', false, 9, 1),
       ('qsdqsdqs', 'Undsfsdfsdfsd cycle fddsfsdf romans...', 'c43a3334-c7b6-4f4b-85e7-e7e5cee2dd5c.jpg', false, 9, 1),
       ('erzerrhfg', 'Un sdfsdfscycle de romans...', 'd5c6e47d-0cbf-493c-882c-d00bd67be61d.jpg', false, 9, 1),
       ('gfhgfhgmkl', 'Un cycle de romans...', 'dec11981-887d-4bc3-a600-8f63127754d7.jpg', false, 9, 1),
       ('fdghfdhfd', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1),
       ('pioupio', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1),
       ('fddfhdfgh', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1),
       ('mklmklm', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1),
       ('azreze', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1),
       ('nvbnvbn', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1),
       ('mlkmklm', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1),
       ('qsqsqs', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1),
       ('piopuipio', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1),
       ('zezezeze', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1)


;

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