package fr.afpa.pompey.cda22045.myyebook.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class AuteurTest {
    private Auteur auteur;

    @BeforeEach
    void setUp() {
        auteur = new Auteur(null, "nomauteur","prenomauteur","../chemin/vers/laphoto/123e4567-e89b-12d3-a456-426614174000.jpg" );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Dupont",
            "Marie",
            "Sartre",
            "Dupont ",
    })
    void setAutNomValid(String autNom) {
        assertDoesNotThrow(() -> auteur.setNom(autNom));
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
    void setAutNomInvalid(String autNom) {
        assertThrows(IllegalArgumentException.class, () -> auteur.setNom(autNom));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "Dupont",
            "Marie",
            "Sartre",
            "Dupont ",
    })
    void setPrenomValid(String cliPrenom) {
        assertDoesNotThrow(() -> auteur.setNom(cliPrenom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "12345",
            "Jean123",
            "Jean Dupont Jean Dupont Jean Dupont Jean Dupont Jean Dupont",
            "mlmlk@"
    })
    void setPrenomInvalid(String cliPrenom) {
        assertThrows(IllegalArgumentException.class, () -> auteur.setPrenom(cliPrenom));
    }

        @ParameterizedTest
    @ValueSource(strings = {"photo.jpg", "image.png", "portrait.jpeg"})
    void setAutPhotoValid(String autPhoto) {
        assertDoesNotThrow(() -> auteur.setPhoto(autPhoto));
    }

    @ParameterizedTest
    @ValueSource(strings = { "abc", "null" })
    void setAutPhotoInvalid(String autPhoto) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            auteur.setPhoto(autPhoto.equals("null") ? null : autPhoto);
        });
        if (autPhoto.equals("null")) {
            assertEquals("Le chemin de la photo de l'auteur ne peut pas etre null", exception.getMessage());
        } else if (autPhoto.length() < 5) {
            assertEquals("Le chemin de la photo de l'auteur est trop court", exception.getMessage());
        }
    }

}
