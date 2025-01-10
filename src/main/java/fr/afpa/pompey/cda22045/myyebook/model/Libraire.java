package fr.afpa.pompey.cda22045.myyebook.model;


import lombok.Getter;

@Getter
public class Libraire extends Compte {
    private Integer libId;
    private String nom;
    private String prenom;

    public Libraire() {
    }

    public Libraire(Integer libId, String login , String password ,String nom, String prenom) {
        setLogin(login);
        setPassword(password);
        setLibId(libId);
        setNom(nom);
        setPrenom(prenom);
    }

    public Libraire(Integer libId, Compte compte ,String nom, String prenom) {
        super(compte.getCompteId(), compte.getLogin(), compte.getPassword());
        setCompteId(libId);
        setNom(nom);
        setPrenom(prenom);
    }


    public void setLibId(Integer libId) {
        if ( libId != null &&  libId <= 0) {
            throw new IllegalArgumentException("L'id ne peut pas etre inferieur ou egal a zero");
        }
        this.libId = libId;
    }

    public void setNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("Le nom du client ne peut pas etre null");
        }
        nom = nom.trim();
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]{2,50}$";
        if (!nom.matches(regex)) {
            throw new IllegalArgumentException("Le nom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de 2 à 50 caractères");
        }
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        if (prenom == null) {
            throw new IllegalArgumentException("Le prenom du client ne peut pas etre null");
        }
        prenom = prenom.trim();
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]{2,50}$";
        if (!prenom.matches(regex)) {
            throw new IllegalArgumentException("Le prenom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de 2 à 50 caractères");
        }
        this.prenom = prenom;
    }






}
