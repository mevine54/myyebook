package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.dao.ClientDAO.ClientDAO;
import fr.afpa.pompey.cda22045.myyebook.dao.ClientDAO.ClientDAOImp;
import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAO;
import fr.afpa.pompey.cda22045.myyebook.dao.comptedao.CompteDAOImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClientTestDAO {

    public ClientDAO clientDAO;
    public Client client;
    public CompteDAO comptDAO;
    
    @BeforeEach
    public void setUp() {
        this.clientDAO = new ClientDAOImp();
        this.client = new Client();
        this.comptDAO = new CompteDAOImp();
    }


    @Test
    public void testInsert() throws SQLException {
        Client client = new Client("qwses", "Tasesttqwwaa",
                "cliesntaaw1qq.test@example.com", "13 Rue de Test", "Test", "54000");


        Compte compte = new Compte();
        compte.setLogin("qwssddh");
        compte.setPassword("asdasd!A2");

        // Insérer le compte et récupérer l'ID généré
        int compteId = comptDAO.insert(compte);
        compte.setCompteId(compteId);

        // Associer le compte au client
        client.setCompteId(compteId);

        // Insérer le client dans la base de données
        int generatedId = clientDAO.insert(client);
//        System.out.println("Id généré: " + generatedId);

        // Récupérer le client depuis la base de données avec l'ID généré
        Client clientDB = clientDAO.get(generatedId);
//        System.out.println("Client récupéré: " + clientDB);

        // Vérifier que le client inséré correspond à celui récupéré
        assertNotNull(clientDB, "Le client n'a pas été trouvé dans la base");
        assertEquals(generatedId, clientDB.getClientId(), "Les IDs des clients ne correspondent pas");

        // Affichage du client pour vérification
        System.out.println(clientDB);
    }


    @Test
    public void testClientGetAll() throws SQLException {
        List<Client> clients = clientDAO.getAll();
        assertNotNull(clients, "La liste des clients ne doit pas être nulle");

        if (clients.isEmpty()) {
            System.out.println("Aucun client enregistré dans la base de données.");
        } else {
            clients.forEach(System.out::println);

            Client client = clients.get(0);
            assertNotNull(client.getPrenom(), "Le prénom du client ne doit pas être nul");
            assertNotNull(client.getNom(), "Le nom du client ne doit pas être nul");

        }



    }


    @Test
    public void testClientUpdate() throws SQLException {
        Client client = clientDAO.get(1);
        assertNotNull(client, "Le client avec l'ID  n'existe pas ");

        String prenom = "Evan";
        client.setPrenom(prenom);

        clientDAO.update(client);

        Client updatedCli= clientDAO.get(1);
        assertNotNull(updatedCli, "Le client mis à jour est introuvable");
        assertEquals(prenom, updatedCli.getPrenom(), "Le prénom n'a pas été mis à jour correctement.");

    }


    @Test
    public void testClientDelete() throws SQLException {
        Client client = clientDAO.get(1);
        assertNotNull(client, "Le client n'existe pas ");
        clientDAO.delete(1);
        Client deletedClient = clientDAO.get(1);
        assertNotNull(deletedClient, "Le client n'existe pas ");
    }


}




