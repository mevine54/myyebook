package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMaximaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMinimaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.RegexValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LibraireTest {

    private Libraire libraire;
    private Compte compte;

    @BeforeEach
    void setUp() {
        libraire = new Libraire("monlogin","motdepasseSecure11!",true,"nomlibraire","prenomlibraire");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Dupont",
            "Marie",
            "Sartre",
            "Dupont ",
    })
    void setNomValid(String nom) {
        assertDoesNotThrow(() -> libraire.setNom(nom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "12345",
            "Jean123",

    })
    void setNomInvalid(String nom) {
        assertThrows(RegexValidationException.class, () -> libraire.setNom(nom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A",
            "",
            " ",
            "@"
    })
    void setNomLongueurMinInvalid(String nom) {
        assertThrows(LongueurMinimaleException.class, () -> libraire.setNom(nom));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "Jean Dupont Jean Dupont Jean Dupont Jean Dupont Jean Dupont",
    })
    void setNomLongueurMaxInvalid(String nom) {
        assertThrows(LongueurMaximaleException.class, () -> libraire.setNom(nom));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "Dupont",
            "Marie",
            "Sartre",
            "Dupont ",
    })
    void setPrenomValid(String prenom) {
        assertDoesNotThrow(() -> libraire.setPrenom(prenom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A*",
            "12345",
            "Jean123",
            "rtyrty@"
    })
    void setPrenomRegexInvalid(String prenom) {
        assertThrows(RegexValidationException.class, () -> libraire.setPrenom(prenom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A",
            "r",
            "o",
    })
    void setPrenomLongueurMinInvalid(String prenom) {
        assertThrows(LongueurMinimaleException.class, () -> libraire.setPrenom(prenom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Jean Dupont Jean Dupont Jean Dupont Jean Dupont Jean Dupont",
    })
    void setPrenomLongueurMaxInvalid(String prenom) {
        assertThrows(LongueurMaximaleException.class, () -> libraire.setPrenom(prenom));
    }


}
