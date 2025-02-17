package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.IdTropPetitException;
import fr.afpa.pompey.cda22045.myyebook.exception.IncoherenteDateException;
import fr.afpa.pompey.cda22045.myyebook.exception.NullValueException;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Emprunter {
    private Integer id;
    private Client client;
    private Libraire libraire;
    private Livre livre;
    private LocalDateTime datetimeEmprunt;
    private LocalDateTime datetimeRetour;
    private LocalDateTime datetimeReservation;



    public Emprunter() {
    }

    public Emprunter( Integer id, Client client, Libraire libraire, Livre livre,LocalDateTime datetimeReservation,  LocalDateTime datetimeEmprunt, LocalDateTime datetimeRetour) {
        setId(id);
        setClient(client);
        setLibraire(libraire);
        setLivre(livre);
        setDatetimeReservation(datetimeReservation);
        setDatetimeEmprunt(datetimeReservation);
        setDatetimeEmprunt(datetimeEmprunt);
        setDatetimeRetour(datetimeRetour);
    }



    public void setId(Integer id) {
        if ( id!=null && id <= 0) {
            throw new IdTropPetitException("L'id ne peut pas etre inferieur ou egal a zero");
        }
        this.id = id;
    }

    public void setClient(Client client) {
        if (client == null) {
            throw new NullValueException("Le client ne peut pas être null");
        }
        this.client = client;
    }

    public void setLivre(Livre livre) {
        if (livre == null) {
            throw new NullValueException("le livre ne peut pas être null");
        }
        this.livre = livre;
    }

    public void setLibraire(Libraire libraire) {
        if (libraire == null) {
            throw new NullValueException("la libraire ne peut pas être null");
        }
        this.libraire = libraire;
    }


    private void setDatetimeReservation(LocalDateTime datetimeReservation) {
        if (datetimeReservation == null) {
            throw new NullValueException("la date de reservation ne peut pas être null");
        } else if (  datetimeEmprunt != null  && datetimeReservation.isBefore(datetimeEmprunt) ) {
            throw new IncoherenteDateException("La date de reservation ne peut pas être avant la date d'emprunt");
        }
        this.datetimeReservation = datetimeReservation;
    }


    public void setDatetimeEmprunt(LocalDateTime datetimeEmprunt) {

        if ( datetimeEmprunt != null && datetimeEmprunt.isAfter(LocalDateTime.now())) {
            throw new IncoherenteDateException("La date d'emprunt ne peut pas être dans le futur");
        }
        this.datetimeEmprunt = datetimeEmprunt;
    }

    public void setDatetimeRetour(LocalDateTime datetimeRetour) {
        if ( datetimeRetour != null && datetimeEmprunt != null &&  datetimeRetour.isBefore(datetimeEmprunt) ) {
            throw new IncoherenteDateException("La date de retour ne peut pas être avant la date d'emprunt");
        }
        this.datetimeRetour = datetimeRetour;
    }

    @Override
    public String toString() {
        return "Emprunter{" +
                "id=" + id +
                ", client=" + client +
                ", libraire=" + libraire +
                ", livre=" + livre +
                ", datetimeEmprunt=" + datetimeEmprunt +
                ", datetimeRetour=" + datetimeRetour +
                ", datetimeReservation=" + datetimeReservation +
                '}';
    }
}

