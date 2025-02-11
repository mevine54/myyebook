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
       ('Molière', 'Jean-Baptiste', 'acd54ba5-a244-4944-894f-82be2078cc05.jpg'),
       ('ghjghj', 'Jean-Baptiste', 'acd54ba5-a244-4944-894f-82be2078cc05.jpg'),
       ('sdfgdsfgert', 'Jean-Baptiste', 'acd54ba5-a244-4944-894f-82be2078cc05.jpg'),
       ('ertertert', 'Jean-Baptiste', 'acd54ba5-a244-4944-894f-82be2078cc05.jpg'),
       ('klmklmjkt', 'Jean-Baptiste', 'acd54ba5-a244-4944-894f-82be2078cc05.jpg'),
       ('bnvnvbnvbn', 'Jean-Baptiste', 'acd54ba5-a244-4944-894f-82be2078cc05.jpg'),
       ('aoiupoilkj', 'Jean-Baptiste', 'acd54ba5-a244-4944-894f-82be2078cc05.jpg'),
       ('wxcwxcwxc', 'Jean-Baptiste', 'acd54ba5-a244-4944-894f-82be2078cc05.jpg')
;

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
       ('Humour'),
       ('azd'),
       ('dqsd'),
       ('dqsdqs'),
       ('azeaze'),
       ('yyrty'),
       ('azaz'),
       ('mlmkl')
;

-- Insertion dans la table Compte


--  POIVRE : "poivre temporaire"
-- 'lib1' :  password1M! : $2b$10$t5E9xZ52kV0A7OAUO.9QROQRGX4tfxEq4JhhOeBkKHTgEtsUBoVHy
-- 'lib2' :  password2M! : $2b$10$lDi1MLHpX3pgoeh3Ee1p5euCpWO5q969gslckrNhXG8ejf9u/K.P2
-- 'cli1' :  password3M! : $2b$10$nSWwXHHlURcnwgwY0cNHJe0F1xsBpgbXaOxHHGoIJ29g0ryzs97Hi
-- 'cli2' :  password4M! : $2b$10$o3tD9FpBjoAX.GuQF.ymEu4Ulc4PxzRYB40ihCi6HzPOayMTxq92y
-- 'cli3' :  password5M! : $2b$10$WtUTnvelpOlHnPtlcO1fdeTwFdY2kJo6ENi4PKkNZJdJaZTwf4RF.
-- 'cli4' :  password6M! : $2b$10$gt5iimroHzXfsUfocgcRJ.iDCv6aB.Z6c9uI7iB.bS0Sc8dAVsqPK
-- 'cli5' :  password7M! : $2b$10$uWOrs3AAg3humy4miQnoOuEC3Evkc3/kYwMbqjw2zCLO8BF6webdm
-- 'cli6' :  password8M! : $2b$10$KFDUeK2.NOgj.QW1ozTNke6MR0CesF2OX4ZOEm71EGoZ0sFg4QYrO
-- 'cli7' :  password9M! : $2b$10$9fzBt/HdyxWxOj9cDHHBcOO7Qhe7W9RbckuTH3/o4hBQvvDt3qCsu
-- 'cli8' :  password10M! : $2b$10$UmUQwuTJkJOE7WkDiQP2F.2M2U5X6RTDzEkQVDCjmaFn9MorQSoiC
-- 'lib3' :  password3M! : $2b$10$FUQOPa761dmPIa/f2Y7nkuvpqsPAUbNuGB6EZXDJ2ptgcvB/bypy6

INSERT INTO Compte (cpt_login, cpt_mdp, cpt_role)
VALUES ('lib1', '$2b$10$t5E9xZ52kV0A7OAUO.9QROQRGX4tfxEq4JhhOeBkKHTgEtsUBoVHy', 'ROLE_LIBRAIRE'),
       ('lib2', '$2b$10$lDi1MLHpX3pgoeh3Ee1p5euCpWO5q969gslckrNhXG8ejf9u/K.P2', 'ROLE_LIBRAIRE'),
       ('cli1', '$2b$10$nSWwXHHlURcnwgwY0cNHJe0F1xsBpgbXaOxHHGoIJ29g0ryzs97Hi', 'ROLE_CLIENT'),
       ('cli2', '$2b$10$o3tD9FpBjoAX.GuQF.ymEu4Ulc4PxzRYB40ihCi6HzPOayMTxq92y', 'ROLE_CLIENT'),
       ('cli3', '$2b$10$WtUTnvelpOlHnPtlcO1fdeTwFdY2kJo6ENi4PKkNZJdJaZTwf4RF.', 'ROLE_CLIENT'),
       ('cli4', '$2b$10$gt5iimroHzXfsUfocgcRJ.iDCv6aB.Z6c9uI7iB.bS0Sc8dAVsqPK', 'ROLE_CLIENT'),
       ('cli5', '$2b$10$uWOrs3AAg3humy4miQnoOuEC3Evkc3/kYwMbqjw2zCLO8BF6webdm', 'ROLE_CLIENT'),
       ('cli6', '$2b$10$KFDUeK2.NOgj.QW1ozTNke6MR0CesF2OX4ZOEm71EGoZ0sFg4QYrO', 'ROLE_CLIENT'),
       ('cli7', '$2b$10$9fzBt/HdyxWxOj9cDHHBcOO7Qhe7W9RbckuTH3/o4hBQvvDt3qCsu', 'ROLE_CLIENT'),
       ('cli8', '$2b$10$UmUQwuTJkJOE7WkDiQP2F.2M2U5X6RTDzEkQVDCjmaFn9MorQSoiC', 'ROLE_CLIENT'),
       ('lib3', '$2b$10$FUQOPa761dmPIa/f2Y7nkuvpqsPAUbNuGB6EZXDJ2ptgcvB/bypy6', 'ROLE_LIBRAIRE_ATTENTE')
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
INSERT INTO Livre (liv_titre, liv_resume, liv_photo, liv_en_avant, aut_id, cat_id,quantite)
VALUES ('Les Misérables', 'Un roman historique...', '1ad2a325-1021-4b83-bebb-db1932517e88.jpg', true, 1, 1,5),
       ('Le Comte de Monte-Cristo', 'Un classique...', '2c196cd4-bd08-4bda-ae39-4f716f9419ea.jpg', false, 2, 1,4),
       ('Germinal', 'Une fresque sociale...', '3ccdd34d-be42-4b21-b966-b5d925aa5d39.jpg', true, 3, 1,3),
       ('L’Étranger', 'Un roman philosophique...', '3d1c8775-e9d1-4533-a385-92723d319713.jpg', false, 4, 4,2),
       ('À la recherche du temps perdu', 'Un chef-d’œuvre...', '6e5ca510-2f7d-4693-bce2-75cafebe4887.jpg', false, 5, 1,5),
       ('Les Mouches', 'Une pièce de théâtre...', '7b26596d-cf18-4f96-a12f-5871025820ae.jpg', true, 6, 3,4),
       ('Vingt mille lieues sous les mers', 'Un roman d’aventure...', '55aae132-faf4-42e1-9c68-95f7a9cf4c63.jpg', true, 7, 2,8),
       ('Madame Bovary', 'Une œuvre réaliste...', '61d56c41-05f3-4e61-8241-e3a8c331af6c.jpg', false, 8, 1,10),
       ('La Comédie humaine', 'Un cycle de romans...', '283ae4b4-776a-48ab-8f61-6d17e018ed37.jpg', false, 9, 1,10),
       ('Le Malade imaginaire', 'Une comédie...', '83479cc1-1cd5-4b7b-aa56-5ba8b109136f.jpg', true, 10, 3,4),
       ('La gdfgdfge', 'Un cycle de romans...', '98704c2d-6347-468e-b662-25d27a82e67e.jpg', false, 9, 1,0),
       ('La za	z	az', 'Un cycle de romans...', '03479322-0fae-42d9-9de1-3aef1883d3fa.jpg', false, 9, 1,0),
       ('La piopio', 'Un cycle de romans...', 'af756329-11c4-4ca6-bfa3-fe4c52910474.jpg', false, 9, 1,0),
       ('La aze', 'Un cycle de romans...', 'b3085602-6b68-4bfe-9f96-d0c508c8d094.jpg', false, 9, 1,0),
       ('Lajhjghje', 'Un cycle de romans...', 'be798d86-d7bd-448d-b253-d5142ad30fe6.jpg', false, 9, 1,0),
       ('qsdqsdqs', 'Undsfsdfsdfsd cycle fddsfsdf romans...', 'c43a3334-c7b6-4f4b-85e7-e7e5cee2dd5c.jpg', false, 9, 1,0),
       ('erzerrhfg', 'Un sdfsdfscycle de romans...', 'd5c6e47d-0cbf-493c-882c-d00bd67be61d.jpg', false, 9, 1,0),
       ('gfhgfhgmkl', 'Un cycle de romans...', 'dec11981-887d-4bc3-a600-8f63127754d7.jpg', false, 9, 1,0),
       ('fdghfdhfd', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1,0),
       ('pioupio', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1,0),
       ('fddfhdfgh', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1,0),
       ('mklmklm', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1,0),
       ('azreze', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1,0),
       ('nvbnvbn', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1,0),
       ('mlkmklm', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1,0),
       ('qsqsqs', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1,0),
       ('piopuipio', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1,0),
       ('zezezeze', 'Un cycle de romans...', 'ed062afb-9c40-44ab-b977-9f9e84c744bb.jpg', false, 9, 1,0)
;


-- Insertion dans la table Emprunter
INSERT INTO Emprunter ( emp_res_date,emp_date_emprunt, emp_date_retour, cli_id, lib_id, liv_id)
VALUES ( '2025-01-09 11:00:00',  '2025-01-10 11:00:00', '2025-01-20 11:00:00', 1, 1, 1),
       ('2025-01-10 13:00:00', '2025-01-11 13:00:00', '2025-01-21 13:00:00', 2, 2, 2),
       ( '2025-01-11 16:00:00', '2025-01-12 16:00:00', NULL, 3, 1, 3),
       ( '2025-01-12 15:30:00','2025-01-13 15:30:00', '2025-01-23 15:30:00', 4, 2, 4);