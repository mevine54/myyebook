package fr.afpa.pompey.cda22045.myyebook.model;

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
        compte = new Compte(1,"monlogin","motdepasseSecure11");
        libraire = new Libraire(null,compte,"nomlibraire","prenomlibraire");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Dupont",
            "Marie",
            "Sartre",
            "Dupont ",
    })
    void setNomValid(String cliNom) {
        assertDoesNotThrow(() -> libraire.setNom(cliNom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A",
            "12345",
            "Jean123",
            "Jean Dupont Jean Dupont Jean Dupont Jean Dupont Jean Dupont",
            "",
            " ",
            "@"
    })
    void setNomInvalid(String cliNom) {
        assertThrows(IllegalArgumentException.class, () -> libraire.setNom(cliNom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Dupont",
            "Marie",
            "Sartre",
            "Dupont ",
    })
    void setPrenomValid(String cliPrenom) {
        assertDoesNotThrow(() -> libraire.setNom(cliPrenom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A",
            "12345",
            "Jean123",
            "Jean Dupont Jean Dupont Jean Dupont Jean Dupont Jean Dupont",
            "",
            " ",
            "@"
    })
    void setPrenomInvalid(String cliPrenom) {
        assertThrows(IllegalArgumentException.class, () -> libraire.setPrenom(cliPrenom));
    }


}
