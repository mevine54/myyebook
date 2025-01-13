package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.*;
import lombok.Getter;

@Getter
public class Client extends Compte {
    private Integer clientId;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String ville;
    private String codePostal;

    public Client() {
    }

    public Client(String login, String password, String nom, String prenom, String email, String adresse, String ville, String codePostal) {
        super(login, password);
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
    }

    public Client(Integer clientId, Compte compte, String nom, String prenom, String email, String adresse, String ville, String codePostal) {
        super(compte.getCompteId(), compte.getLogin(), compte.getPassword());
        setClientId(clientId);
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
    }

    public Client(Integer clientId, Integer compteId ,String login , String password, String nom, String prenom, String email, String adresse, String ville, String codePostal) {
        super(compteId, login, password);
        setClientId(clientId);
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
    }

    public void setClientId(Integer clientid) {
        if (clientId != null && clientId <= 0) {
            throw new IdTropPetitException("L'Id ne peut pas etre inferieur ou egal a zero");
        }
        this.clientId = clientId;
    }

    public void setNom(String nom) {

        int longueurMin = 2;
        int longueurMax = 50;
        if (nom == null) {
            throw new NullValueException("Le nom du client ne peut pas etre null");
        }
        nom = nom.trim();
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]{" + longueurMin + "," + longueurMax + "}$";
        if (nom.length() < longueurMin) {
            throw new LongueurMinimaleException("Le nom du client est trop court:" + nom + ", " + nom.length() + " caracteres");
        } else if (nom.length() > longueurMax) {
            throw new LongueurMaximaleException("Le nom du client est trop long:" + nom + ", " + nom.length() + " caracteres");
        } else  if (!nom.matches(regex)) {
            throw new RegexValidationException("Le nom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de " + longueurMin + " à " + longueurMax + " caractères");
        }
        this.nom = nom;
    }

    public void setPrenom(String prenom) {

        int longueurMin = 2;
        int longueurMax = 50;
        if (prenom == null) {
            throw new NullValueException("Le prenom du client ne peut pas etre null");
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

    public void setEmail(String email) {
        if (email == null) {
            throw new NullValueException("L Email ne peut pas être null");
        } else if (email.trim().toLowerCase().matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
            this.email = email.trim().toLowerCase();
        } else {
            throw new RegexValidationException("L'addresse mail n'est pas valide: " + this.email.trim());
        }

    }


    public void setAdresse(String adresse) {
        String regex = "^\\d+\\s+[a-zA-Zéî'çùâûôàÉl’'\\s\\-]+$";
        if (adresse != null && (adresse.matches(regex))) {
            this.adresse = adresse;
        } else {
            throw new RegexValidationException("L'addresse n'est pas valide : " + adresse);
        }

    }

    public void setVille(String ville) {
        if (ville== null){
            throw new NullValueException("La ville ne peut pas être null");
        }
        if ((!ville.isBlank()) && ville.matches("^[a-zA-Z ]*[-a-zA-Z ]*$")) {
            ville = ville.toLowerCase().trim();
            ville = ville.substring(0, 1).toUpperCase() + ville.substring(1);
            this.ville = ville;
        } else {
            throw new RegexValidationException("La ville n'est pas valide");
        }
    }

    public void setCodePostal(String codePostal) {
        String regex = "^(?:0[1-9]|[1-8]\\d|9[0-8]|2[ABab]|97[1-6])\\d{3}$";
        if (codePostal != null && (codePostal.toLowerCase().matches(regex))) {
            this.codePostal = codePostal.toUpperCase();
        } else {
            throw new RegexValidationException("Le code postale n'est pas valide: " + this.codePostal);
        }
    }


    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal='" + codePostal + '\'' +
                '}';
    }
}
