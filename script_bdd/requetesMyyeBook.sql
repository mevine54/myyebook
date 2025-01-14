SELECT * FROM Libraire;

-- Requête pour afficher les clients et leurs comptes associés
SELECT 
    c.cli_nom, 
    c.cli_prenom, 
    cp.cpt_login 
FROM 
    Client c
JOIN 
    Compte cp ON c.cpt_id = cp.cpt_id;

-- Requête pour afficher les livres empruntés par chaque client
SELECT 
    cl.cli_nom, 
    cl.cli_prenom, 
    l.liv_titre, 
    e.emp_date_emprunt, 
    e.emp_date_retour 
FROM 
    Emprunter e
JOIN 
    Client cl ON e.cli_id = cl.cli_id
JOIN 
    Exemplaire ex ON e.exe_id = ex.exe_id
JOIN 
    Livre l ON ex.liv_id = l.liv_id;


-- Récupérer les livres d'une catégorie donnée
SELECT 
    l.liv_titre, 
    l.liv_resume, 
    c.cat_nom 
FROM 
    Livre l
JOIN 
    Categorie c ON l.cat_id = c.cat_id
WHERE 
    c.cat_nom = 'Roman';

-- Récupérer les exemplaires disponibles pour un livre donné
SELECT 
    e.exe_id, 
    l.liv_titre 
FROM 
    Exemplaire e
JOIN 
    Livre l ON e.liv_id = l.liv_id
WHERE 
    l.liv_titre = 'Les Misérables';

-- Afficher les réservations d’un client avec leurs dates
SELECT 
    r.res_id, 
    r.res_date, 
    l.liv_titre, 
    c.cli_nom, 
    c.cli_prenom 
FROM 
    Reservation r
JOIN 
    Livre l ON r.liv_id = l.liv_id
JOIN 
    Client c ON r.cli_id = c.cli_id
WHERE 
    c.cli_nom = 'Dupont';

-- Afficher les emprunts en cours (sans date de retour)
SELECT 
    e.emp_id, 
    l.liv_titre, 
    c.cli_nom, 
    c.cli_prenom, 
    e.emp_date_emprunt 
FROM 
    Emprunter e
JOIN 
    Client c ON e.cli_id = c.cli_id
JOIN 
    Exemplaire ex ON e.exe_id = ex.exe_id
JOIN 
    Livre l ON ex.liv_id = l.liv_id
WHERE 
    e.emp_date_retour IS NULL;


-- Lister les livres les plus empruntés
SELECT 
    l.liv_titre, 
    COUNT(e.exe_id) AS nb_emprunts 
FROM 
    Emprunter e
JOIN 
    Exemplaire ex ON e.exe_id = ex.exe_id
JOIN 
    Livre l ON ex.liv_id = l.liv_id
GROUP BY 
    l.liv_titre
ORDER BY 
    nb_emprunts DESC;
