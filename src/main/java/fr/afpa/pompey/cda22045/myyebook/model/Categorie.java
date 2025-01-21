package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.*;
import lombok.Getter;

@Getter
public class Categorie {
    private Integer id;
    private String nom;

    public Categorie(Integer id, String nom) {
        setId(id);
        setNom(nom);
    }

    public void setId(Integer id) {
        if (id != null && id <= 0) {
            throw new IdTropPetitException("L'id ne peut pas etre inferieur ou egal a zero");
        }
        this.id = id;
    }

    public void setNom(String nom) {

        int longueurMin = 2;
        int longueurMax = 50;
        if (nom == null) {
            throw new NullValueException("Le nom de la categorie ne peut pas etre null");
        }
        nom = nom.trim();
        String regex = "^[A-Za-zàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ\\-\\s]{" + longueurMin + "," + longueurMax + "}$";
        if (nom.length() < longueurMin) {
            throw new LongueurMinimaleException("Le nom de la categorie est trop court:" + nom + ", " + nom.length() + " caracteres");
        } else if (nom.length() > longueurMax) {
            throw new LongueurMaximaleException("Le nom de la categorie est trop long:" + nom + ", " + nom.length() + " caracteres");
        } else  if (!nom.matches(regex)) {
            throw new RegexValidationException("Le nom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de " + longueurMin + " à " + longueurMax + " caractères");
        }
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
