package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMaximaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMinimaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.RegexValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientTest {

    private Client client;
    private Compte compte;

    @BeforeEach
    void setUp() {
        compte = new Compte(1,"monlogin","motdepasseSecure1!","ROLE_CLIENT");

        client = new Client(compte,null,"nomclient","prenomclient","email@example.com","12 rue blabla","ville","54000");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Dupont",
            "Marie",
            "Sartre",
            "Dupont ",
    })
    void setNomValid(String nom) {
        assertDoesNotThrow(() -> client.setNom(nom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "12345",
            "Jean123",

    })
    void setNomInvalid(String nom) {
        assertThrows(RegexValidationException.class, () -> client.setNom(nom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A",
            "",
            " ",
            "@"
    })
    void setNomLongueurMinInvalid(String nom) {
        assertThrows(LongueurMinimaleException.class, () -> client.setNom(nom));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "Jean Dupont Jean Dupont Jean Dupont Jean Dupont Jean Dupont",
    })
    void setNomLongueurMaxInvalid(String nom) {
        assertThrows(LongueurMaximaleException.class, () -> client.setNom(nom));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "Dupont",
            "Marie",
            "Sartre",
            "Dupont ",
    })
    void setPrenomValid(String prenom) {
        assertDoesNotThrow(() -> client.setPrenom(prenom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A*",
            "12345",
            "Jean123",
            "rtyrty@"
    })
    void setPrenomRegexInvalid(String prenom) {
        assertThrows(RegexValidationException.class, () -> client.setPrenom(prenom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A",
            "r",
            "o",
    })
    void setPrenomLongueurMinInvalid(String prenom) {
        assertThrows(LongueurMinimaleException.class, () -> client.setPrenom(prenom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Jean Dupont Jean Dupont Jean Dupont Jean Dupont Jean Dupont",
    })
    void setPrenomLongueurMaxInvalid(String prenom) {
        assertThrows(LongueurMaximaleException.class, () -> client.setPrenom(prenom));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@example.com", "user.name@domain.co", "valid.email@sub.domain.com"})
    public void testEmailValid(String email) {
        assertDoesNotThrow(() -> client.setEmail(email));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "invalid-email",
            "user@domain",
            "user@.com",
            "user@domain..com"
    })
    public void testEmailInvalid(String email) {
        assertThrows(RegexValidationException.class, () -> client.setEmail(email));
    }

    //TODO: adresse
    //TODO: city
    //TODO: code postale

}
