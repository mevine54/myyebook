package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Auteur {
    private Integer auteurId;
    private String nom;
    private String prenom;
    private String photo;

    public Auteur() {
    }

    public Auteur(Integer auteurId, String nom, String prenom, String photo) {
        setAuteurId(auteurId);
        setNom(nom);
        setPrenom(prenom);
        setPhoto(photo);
    }


    public void setAuteurId(Integer auteurId) {
        if (auteurId != null && auteurId <= 0) {
            throw new IdTropPetitException("L'id ne peut pas etre inferieur ou egal a zero");
        }
        this.auteurId = auteurId;
    }

    public void setNom(String nom) {
        int longueurMin = 2;
        int longueurMax = 50;
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]{" + longueurMin + "," + longueurMax +"}$";
        if (nom == null) {
            throw new NullValueException("Le nom de l'auteur ne peut pas etre null");
        }
        nom = nom.trim();
        if (nom.length() < longueurMin) {
            throw new LongueurMinimaleException("Le nom de l'auteur est trop court:" + nom + ", " + nom.length() + " caracteres");
        } else if (nom.length() > longueurMax) {
            throw new LongueurMaximaleException("Le nom de l'auteur est trop long:" + nom + ", " + nom.length() + " caracteres");
        } else if (!nom.matches(regex)) {
            throw new RegexValidationException("Le nom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de " + longueurMin +" à "+ longueurMax +" caractères");
        }
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        int longueurMin = 0;
        int longueurMax = 50;
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]{" + longueurMin + "," + longueurMax + "}$";
        if (prenom == null) {
            throw new NullValueException("Le prenom de l'auteur ne peut pas etre null");
        }
        prenom = prenom.trim();

        if (prenom.length() < longueurMin) {
            throw new LongueurMinimaleException("Le prenom de l'auteur est trop court:" + prenom + ", " + prenom.length() + " caracteres");
        } else if (prenom.length() > longueurMax) {
            throw new LongueurMaximaleException("Le prenom de l'auteur est trop long:" + prenom + ", " + prenom.length() + " caracteres");
        } else if (!prenom.matches(regex)) {
            throw new RegexValidationException("Le prenom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de " + longueurMin + " à " + longueurMax + " caractères");
        }
        this.prenom = prenom;
    }

    public void setPhoto(String photo) {
        int longueurMin = 6;
        String regex  = ".*\\/[a-zA-Z0-9-]*\\.(jpg|png|gif|jpeg|bmp)$";
        if (photo == null) {
            throw new NullValueException("Le chemin de la photo de l'auteur ne peut pas etre null");
        } else if (photo.length() < longueurMin) {
            throw new LongueurMinimaleException("Le chemin de la photo de l'auteur est trop court");
        } else if (!photo.matches(regex)) {
            throw new RegexValidationException("Le chemin de la photo de l'auteur n est pas valide .Veuillez choisir un format d'image");
        }
        this.photo = photo;
    }



    @Override
    public String toString() {
        return "Auteur{" +
                "auteurId=" + auteurId +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }


}
