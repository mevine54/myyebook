package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.IdTropPetitException;
import fr.afpa.pompey.cda22045.myyebook.exception.IncoherenteDateException;
import fr.afpa.pompey.cda22045.myyebook.exception.NullValueException;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Emprunter {
    private Integer id;
    private Reservation reservation;
    private Client client;
    private Libraire libraire;
    private Exemplaire exemplaire;
    private LocalDateTime datetimeEmprunt;
    private LocalDateTime datetimeRetour;

    public Emprunter() {
    }

    public Emprunter( Integer id, Client client, Libraire libraire, Exemplaire exemplaire, LocalDateTime datetimeEmprunt, LocalDateTime datetimeRetour, Reservation reservation) {
        setId(id);
        setClient(client);
        setLibraire(libraire);
        setExemplaire(exemplaire);
        setDatetimeEmprunt(datetimeEmprunt);
        setDatetimeRetour(datetimeRetour);
        setReservation(reservation);
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

    public void setExemplaire(Exemplaire exemplaire) {
        if (exemplaire == null) {
            throw new NullValueException("l 'exemplaire ne peut pas être null");
        }
        this.exemplaire = exemplaire;
    }

    public void setLibraire(Libraire libraire) {
        if (libraire == null) {
            throw new NullValueException("la libraire ne peut pas être null");
        }
        this.libraire = libraire;
    }

    public void setReservation(Reservation reservation) {
        if (reservation != null && reservation.getDatetime().isAfter(datetimeEmprunt)  ) {
            throw new IncoherenteDateException("La date de reservation ne peut pas etre posterieur à la date d'emprunt");
        }
        this.reservation = reservation;
    }

    public void setDatetimeEmprunt(LocalDateTime datetimeEmprunt) {

        if (datetimeEmprunt == null) {
            throw new NullValueException("la date d emprunt ne peut pas être null");
        } else if (datetimeEmprunt.isAfter(LocalDateTime.now())) {
            throw new IncoherenteDateException("La date d'emprunt ne peut pas être dans le futur");
        }

        this.datetimeEmprunt = datetimeEmprunt;
    }

    public void setDatetimeRetour(LocalDateTime datetimeRetour) {
        if (datetimeRetour.isBefore(datetimeEmprunt) ) {
            throw new IncoherenteDateException("La date de retour ne peut pas être avant la date d'emprunt");
        }
        this.datetimeRetour = datetimeRetour;
    }

    @Override
    public String toString() {
        return "Emprunter{" +
                "id=" + id +
                ", reservation=" + reservation +
                ", client=" + client +
                ", libraire=" + libraire +
                ", exemplaire=" + exemplaire +
                ", datetimeEmprunt=" + datetimeEmprunt +
                ", datetimeRetour=" + datetimeRetour +
                '}';
    }
}

