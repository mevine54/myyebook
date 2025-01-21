package fr.afpa.pompey.cda22045.myyebook.model;


import fr.afpa.pompey.cda22045.myyebook.exception.*;
import lombok.*;

@Getter
public class Compte {

    private Integer compteId;
    private String login;
    private String password;

    public Compte(){
    }

    public Compte(String login, String password) {
        setLogin( login);
        setPassword(password);
    }

    public Compte(Integer compteId, String login, String password) {
        this(login,password);
        setCompteId( compteId );
    }

    public void setCompteId(Integer compteId) {
        if ( compteId!=null && compteId <= 0) {
            throw new IdTropPetitException("L'id ne peut pas etre inferieur ou egal a zero");
        }
        this.compteId = compteId;
    }

    public void setLogin(String login) {
        int maxLen = 15;
        int minLen = 4;
        String regex = "^[a-zA-Z0-9]{" + minLen + "," + maxLen + "}$";

        if (login == null) {
            throw new NullValueException("L'identifiant ne peut pas être null");
        }

        login  = login.trim();
        if (login.length() < minLen) {
            throw new LongueurMinimaleException("L'identifiant doit avoir au minimum " + minLen + " caractères");
        } else if (login.length() > maxLen) {
            throw new LongueurMaximaleException("L'identifiant doit avoir au maximum " + maxLen + " caractères");
        } else if (!login.matches(regex)) {
            throw new RegexValidationException("L'identifiant ne doit contenir que des lettres et des chiffres");
        }
        this.login = login;
    }

    public void setPassword(String password) {
        int maxLen = 150;
        int minLen = 8;
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{" + minLen + "," + maxLen + "}$";

        if (password == null) {
            throw new NullValueException("Le mot de passe ne peut pas être null");
        } else if (password.length() < minLen) {
            throw new LongueurMinimaleException("Le mot de passe doit avoir au minimum " + minLen + " caractères");
        } else if (password.length() > maxLen) {
            throw new LongueurMaximaleException("Le mot de passe doit avoir au maximum " + maxLen + " caractères");
        } else if (!password.matches(regex)) {
            throw new RegexValidationException("Le mot de passe doit contenir au moins " + minLen + " caractères, une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial: @$!%*?& ");
        }
        // TODO: Hash password
        this.password = password;

    }

    @Override
    public String toString() {

        return "Compte{" +
                "cptId=" + compteId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';

    }
}
