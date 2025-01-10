package fr.afpa.pompey.cda22045.myyebook.model;

import lombok.Getter;

@Getter
public class Auteur {
    private Integer auteurId;
    private String nom;
    private String prenom;
    private String photo;

    public Auteur(Integer auteurId, String nom, String prenom, String photo) {
        setAuteurId(auteurId);
        setNom(nom);
        setPrenom(prenom);
        setPhoto(photo);
    }

    public void setAuteurId(Integer auteurId) {
        if (auteurId != null && auteurId <= 0) {
            throw new IllegalArgumentException("L'id ne peut pas etre inferieur ou egal a zero");
        }
        this.auteurId = auteurId;
    }

    public void setNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("Le nom de l'auteur ne peut pas etre null");
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
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]{0,50}$";
        if (!prenom.matches(regex)) {
            throw new IllegalArgumentException("Le prenom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de 0 à 50 caractères");
        }
        this.prenom = prenom;
    }

    public void setPhoto(String photo) {
        if (photo == null) {
            throw new IllegalArgumentException("Le chemin de la photo de l'auteur ne peut pas etre null");
        } else if (photo.length() < 5) {
            throw new IllegalArgumentException("Le chemin de la photo de l'auteur est trop court");
        }
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "autId=" + auteurId +
                ", autNom='" + nom + '\'' +
                ", autPhoto='" + photo + '\'' +
                '}';
    }
}
