package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.ConnectionBDD.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DatabaseConnectionTest {

    @BeforeEach
    void setUp() {
        Connection connection = DatabaseConnection.getConnection();
    }

    @Test
    void insertCompteTest(){

    }

}
