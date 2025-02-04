package fr.afpa.pompey.cda22045.myyebook.dao;

import fr.afpa.pompey.cda22045.myyebook.dao.clientdao.ClientDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAOImp;
import fr.afpa.pompey.cda22045.myyebook.model.Client;
import fr.afpa.pompey.cda22045.myyebook.model.Compte;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

@Slf4j
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
            log.info(String.valueOf(client));
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void getAllValid() {
        try {
            List<Client> clients  = clientDAOImp.getAll();
            log.info(clients.toString());
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void insertValid() {
        try {
            Compte compte = new Compte(1,"monlogin","motdepasseSecure1!","ROLE_CLIENT");

            Client client = new Client(compte,null,"Nomclient","PrenomClient","azeazreazre@gmail.com","10 azeaze","ytryr","44444");
            Integer id  = clientDAOImp.insert(client);
            log.info("L'id de la client insere est "+ id);
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }

    @Test
    void updateValid() {
        try {
            Client client = clientDAOImp.get(9);
            log.info(String.valueOf(client));
            client.setNom("BLABLANOM");
            client.setPrenom("BLABLAPRENOM");
            Integer id  = clientDAOImp.update(client);
            log.info("L'id de la client modifie est "+ id);
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }


    @Test
    void deleteValid() {
        try {
            Integer id  = 9;
            clientDAOImp.delete(id);
            log.info("L'id de la client "+ id + " a bien ete supprimer");
        } catch (SQLException e) {
            log.info("Exception SQL: " + e.getMessage() );
        }
    }
}
