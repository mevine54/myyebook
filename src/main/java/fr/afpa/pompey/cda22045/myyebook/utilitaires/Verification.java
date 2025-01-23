package fr.afpa.pompey.cda22045.myyebook.utilitaires;

import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMaximaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMinimaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.NullValueException;
import fr.afpa.pompey.cda22045.myyebook.exception.RegexValidationException;

public class Verification {
    public static boolean CHARACTER(String character) {
        int longueurMin = 2;
        int longueurMax = 50;
        String regex = Regex.REGEXCHARACTER + longueurMin + "," + longueurMax +"}$";
        if (character == null) {
            throw new NullValueException("Le nom de " +character+ " ne peut pas etre null");
        }
        character = character.trim();
        if (character.length() < longueurMin) {
            throw new LongueurMinimaleException("Le nom de " +character+ "  est trop court:" + character + ", " + character.length() + " caracteres");
        } else if (character.length() > longueurMax) {
            throw new LongueurMaximaleException("Le nom de " +character+ " est trop long:" + character + ", " + character.length() + " caracteres");
        } else if (!character.matches(regex)) {
            new RegexValidationException("Le nom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de " + longueurMin +" à "+ longueurMax +" caractères");
        }
        return true;
    }

}
