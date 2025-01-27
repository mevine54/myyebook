package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.dao.clientdao.ClientDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class ClientDAOImpTest {

    ClientDAOImp clientDAOImp;
    CompteDAOImp compteDAOImp;

    @BeforeEach
    void setUp() {
        clientDAOImp = new ClientDAOImp();
        compteDAOImp = new CompteDAOImp();
    }

    @Test
    void getByIdValid() {
        try {
            Client client = clientDAOImp.get(1);
            System.out.println(client);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Client> clients  = clientDAOImp.getAll();
            System.out.println(clients);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            Client client = new Client("lomdfe","mqsdqsdqscxv12@L","Nomclient","PrenomClient","azeazreazre@gmail.com","10 azeaze","ytryr","44444");
            Integer id  = clientDAOImp.insert(client);
            System.out.println("L'id de la client insere est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void updateValid() {
        try {
            Client client = clientDAOImp.get(9);
            System.out.println(client);
            client.setNom("BLABLANOM");
            client.setPrenom("BLABLAPRENOM");
            Integer id  = clientDAOImp.update(client);
            System.out.println("L'id de la client modifie est "+ id);
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }


    @Test
    void deleteValid() {
        try {
            Integer id  = 9;
            clientDAOImp.delete(id);
            System.out.println("L'id de la client "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            System.out.println("Exception SQL: " + e.getMessage() );
        }
    }
}
