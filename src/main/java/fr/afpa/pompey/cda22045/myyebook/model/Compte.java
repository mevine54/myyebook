package fr.afpa.pompey.cda22045.myyebook.model;


import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import fr.afpa.pompey.cda22045.myyebook.exception.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
public class Compte {

    private Integer compteId;
    private String login;
    private String password;
    private String role;
    protected static final String POIVRE = "DSEFGHVJKGYHXDFCGHVBFGGYHVJHKhjkjh";

    public Compte() {
    }

    public Compte(String login, String password) {
        setLogin(login);
        setPassword(password, POIVRE);
    }

    public Compte(Integer compteId, String login, String password) {
        this(login, password);
        setCompteId(compteId);
    }

    public Compte(Integer compteId, String login, String password,String role) {
        this(login, password);
        setCompteId(compteId);
        setRole(role);
    }

    public void setCompteId(Integer compteId) {
        if (compteId != null && compteId <= 0) {
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

        login = login.trim();
        if (login.length() < minLen) {
            throw new LongueurMinimaleException("L'identifiant doit avoir au minimum " + minLen + " caractères");
        } else if (login.length() > maxLen) {
            throw new LongueurMaximaleException("L'identifiant doit avoir au maximum " + maxLen + " caractères");
        } else if (!login.matches(regex)) {
            throw new RegexValidationException("L'identifiant ne doit contenir que des lettres et des chiffres");
        }
        this.login = login;
    }

    public void setPassword(String password,String poivre) {
        int maxLen = 150;
        int minLen = 8;
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~])[A-Za-z\\d!\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~]{" + minLen + "," + maxLen + "}$";

        if (password == null) {
            throw new NullValueException("Le mot de passe ne peut pas être null");
        } else if (password.length() < minLen) {
            throw new LongueurMinimaleException("Le mot de passe doit avoir au minimum " + minLen + " caractères");
        } else if (password.length() > maxLen) {
            throw new LongueurMaximaleException("Le mot de passe doit avoir au maximum " + maxLen + " caractères");
        } else if (!password.matches(regex)) {
            throw new RegexValidationException("Le mot de passe doit contenir au moins " + minLen + " caractères, une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial");
        }

        if (poivre == null || poivre.isEmpty()) {
            throw new IllegalArgumentException("Le poivre ne peut pas être null");
        }
        // TODO: Hash password et un poivre
//        Hash hash = Password.hash(password).addPepper(poivre).withBcrypt();
//        this.password = hash.toString();
        Hash hash = Password.hash(password).addPepper(poivre).withBcrypt();
        this.password = hash.getResult(); // Stocker uniquement le hash
    }

    protected void setRole(String role) {
        if (role == null) {
            throw new NullValueException("Le role ne peut pas être null");
        } else if (role.equals("ROLE_LIBRAIRE") || role.equals("ROLE_CLIENT") || role.equals("ROLE_LIBRAIRE_ATTENTE")) {
            this.role = role;
        }
        else{
            throw new IllegalArgumentException("Le role n'est pas valide: " + role);
        }
    }

    @Override
    public String toString() {
        return "Compte{" +
                "compteId=" + compteId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
