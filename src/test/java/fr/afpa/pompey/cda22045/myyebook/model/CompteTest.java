package fr.afpa.pompey.cda22045.myyebook.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompteTest {
    private Compte compte;

    @BeforeEach
    void setUp() {
        compte = new Compte(null, "Monlogin", "motdepasseSecure11!");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10})
    public void id_InfZeroInvalid(Integer id) {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.setCompteId(id);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"az", "", "0"})
    public void login_MinLongueurInvalid(String login) {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.setLogin(login);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"azyututyutyutyutyutyutyutyuytutyu", "dfgdfgaqazazeazezaezazae041ret1rgffd0"})
    public void login_MaxLongueurInvalid(String login) {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.setLogin(login);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"az@L", "f11gd@", "#0Mkk"})
    public void password_MinLongueurInvalid(String password) {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.setPassword(password);
        });
    }


    @ParameterizedTest
    @ValueSource(strings = {"az@L1gghh", "Lf11gd@", "jjkj#0Mkk"})
    public void password_MaxLongueurInvalid(String password) {

        for (int i = 0; i < 20; i++) {
            password += password;
        }
        String finalPassword = password;
        assertThrows(IllegalArgumentException.class, () -> {
            compte.setPassword(finalPassword);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"az@LaaeazeEEEEE1", "f11gLGGgghgfhd#", "#0Mkkhhh3hhhhhhhh"})
    public void password_Valid(String password) {
        assertDoesNotThrow(() ->
                compte.setPassword(password)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"azL4777777777", "f1gd@@@@@@@@@", "#0MMMMMMMMMM"})
    public void password_CaractereManquantInvalid(String password) {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.setPassword(password);
        });
    }

}


