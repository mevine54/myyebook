package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMaximaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMinimaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.NullValueException;
import fr.afpa.pompey.cda22045.myyebook.exception.RegexValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void setNomValid(String nom) {
        assertDoesNotThrow(() -> auteur.setNom(nom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "12345",
            "Jean123",
    })
    void setNomRegexInvalid(String nom) {
        assertThrows(RegexValidationException.class, () -> auteur.setNom(nom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Jean Dupont Jean Dupont Jean Dupont Jean Dupont Jean Dupont",
    })
    void setNomLongueurMaxInvalid(String nom) {
        assertThrows(LongueurMaximaleException.class, () -> auteur.setNom(nom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A",
            "",
            " ",
            "@"
    })
    void setAutNomLongueurMinInvalid(String nom) {
        assertThrows(LongueurMinimaleException.class, () -> auteur.setNom(nom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Dupont",
            "Marie",
            "Sartre",
            "Dupont ",
    })
    void setPrenomValid(String prenom) {
        assertDoesNotThrow(() -> auteur.setPrenom(prenom));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "12345",
            "Jean123",
            "mlmlk@"
    })
    void setPrenomInvalid(String prenom) {
        assertThrows(RegexValidationException.class, () -> auteur.setPrenom(prenom));
    }



    @ParameterizedTest
    @ValueSource(strings = {
            "Jonmsdfsdrfsdfsdfsdvxcvxcvxcvxcvxcvxqsdfsdfsdfsdqfsdfsdfsdqfsqdfsdfsdf",
            "Jean Dupont Jean Dupont Jean Dupont Jean Dupont Jean Dupont",
    })
    void setPrenomLongueurMaximaleInvalid(String prenom) {
        assertThrows(LongueurMaximaleException.class, () -> auteur.setPrenom(prenom));
    }


        @ParameterizedTest
    @ValueSource(strings = {"/photo.jpg", "/image.png", "/portrait.jpeg"})
    void setAutPhotoValid(String autPhoto) {
        assertDoesNotThrow(() -> auteur.setPhoto(autPhoto));
    }

    @ParameterizedTest
    @ValueSource(strings = { "a.jpg", "1.bmp" })
    void setAutPhotoLongueurMinimalInvalid(String autPhoto) {
        assertThrows(LongueurMinimaleException.class, () -> auteur.setPhoto(autPhoto));
    }

    @Test
    void setAutPhotoNullInvalid() {
        assertThrows(NullValueException.class, () -> auteur.setPhoto(null));

    }
}
