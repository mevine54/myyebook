package fr.afpa.pompey.cda22045.myyebook.model;

import lombok.Getter;

@Getter
public class Client extends Compte {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;

    public Client() {
    }

    public Client(Integer id, String login, String password, String nom, String prenom, String email) {
        super(null, login, password);
        setCompteId(id);
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
    }

    public Client(Integer id, Compte compte, String nom, String prenom, String email) {
        super(compte.getCompteId(), compte.getLogin(), compte.getPassword());
        setCompteId(id);
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
    }

    public void setCompteId(Integer compteId) {
        if (compteId != null && compteId <= 0) {
            throw new IllegalArgumentException("L'Id ne peut pas etre inferieur ou egal a zero");
        }
        this.id = compteId;
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




    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("L Email ne peut pas être null");
        } else if ((email != null) && email.trim().toLowerCase().matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
            this.email = email.trim().toLowerCase();
        } else {
            throw new IllegalArgumentException("L'addresse mail n'est pas valide: " + this.email);
        }

    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
