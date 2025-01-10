package fr.afpa.pompey.cda22045.myyebook.model;

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
        compte = new Compte(1,"monlogin","motdepasseSecure11");
        client = new Client(null,compte,"nomclient","prenomclient","email@example.com");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Dupont",
            "Marie",
            "Sartre",
            "Dupont ",
    })
    void setNomValid(String cliNom) {
        assertDoesNotThrow(() -> client.setNom(cliNom));
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
        assertThrows(IllegalArgumentException.class, () -> client.setNom(cliNom));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Dupont",
            "Marie",
            "Sartre",
            "Dupont ",
    })
    void setPrenomValid(String cliPrenom) {
        assertDoesNotThrow(() -> client.setNom(cliPrenom));
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
        assertThrows(IllegalArgumentException.class, () -> client.setPrenom(cliPrenom));
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
        assertThrows(IllegalArgumentException.class, () -> client.setEmail(email));
    }

}
