package fr.afpa.pompey.cda22045.myyebook.model;


import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMaximaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMinimaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.NullValueException;
import fr.afpa.pompey.cda22045.myyebook.exception.RegexValidationException;
import lombok.Getter;

@Getter
public class Libraire extends Compte {
    private Integer libId;
    private String nom;
    private String prenom;

    public Libraire() {
    }

    public Libraire(String login , String password ,String nom, String prenom) {
        super(login,password);
        setNom(nom);
        setPrenom(prenom);
    }

    public Libraire(Integer libId,Integer compteId, String login , String password ,String nom, String prenom) {
        super(compteId,login,password);
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
        int longueurMin = 2;
        int longueurMax = 50;
        if (nom == null) {
            throw new NullValueException("Le nom de la libraire ne peut pas etre null");
        }
        if (nom == null) {
            throw new NullValueException("Le nom de la libraire ne peut pas etre null");
        }


        nom = nom.trim();
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]{" + longueurMin + "," + longueurMax + "}$";
        if (nom.length() < longueurMin) {
            throw new LongueurMinimaleException("Le nom de la libraire est trop court:" + nom + ", " + nom.length() + " caracteres");
        } else if (nom.length() > longueurMax) {
            throw new LongueurMaximaleException("Le nom de la libraire est trop long:" + nom + ", " + nom.length() + " caracteres");
        } else if (!nom.matches(regex)) {
            throw new RegexValidationException("Le nom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de " + longueurMin + " à " + longueurMax + " caractères");
        }
        this.nom = nom;
    }

    public void setPrenom(String prenom) {

        int longueurMin = 2;
        int longueurMax = 50;
        if (prenom == null) {
            throw new NullValueException("Le prenom de la libraire ne peut pas etre null");
        }
        prenom = prenom.trim();
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]{" + longueurMin + "," + longueurMax + "}$";
        if (prenom.length() < longueurMin) {
            throw new LongueurMinimaleException("Le mot de passe doit avoir au minimum " + longueurMin + " caractères");
        } else if (prenom.length() > longueurMax) {
            throw new LongueurMaximaleException("Le mot de passe doit avoir au maximum " + longueurMax + " caractères");
        } else if (!prenom.matches(regex)) {
            throw new RegexValidationException("Le prenom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de " + longueurMin + " à " + longueurMax + " caractères");
        }
        this.prenom = prenom;
    }




}
