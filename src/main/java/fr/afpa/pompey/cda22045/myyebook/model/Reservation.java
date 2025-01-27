package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.IdTropPetitException;
import fr.afpa.pompey.cda22045.myyebook.exception.IncoherenteDateException;
import fr.afpa.pompey.cda22045.myyebook.exception.NullValueException;
import lombok.Getter;

import java.awt.print.Book;
import java.time.LocalDateTime;

@Getter
public class Reservation {
    private Integer resId;
    private Client client;
    private Livre livre;
    private LocalDateTime datetime = LocalDateTime.now();

    public Reservation(Integer resId) {
    }

//    public Reservation(Integer resId, LocalDateTime datetime, Livre livre) {
//        setResId(resId);
//        setDatetime(datetime);
//        setLivre(livre);
//    }

    public Reservation(Integer resId, Client client, Livre livre) {
        setResId(resId);
        setClient(client);
        setLivre(livre);
    }

    public Reservation(Integer resId, Client client, Livre livre, LocalDateTime datetime) {
        setResId(resId);
        setClient(client);
        setLivre(livre);
        setDatetime(datetime);
    }

    public Reservation() {

    }

    public void setResId(Integer resId) {
        if (resId != null && resId <= 0) {
            throw new IdTropPetitException("L'id ne peut pas etre inferieur ou egal a zero");
        }
        this.resId = resId;
    }

    public void setClient(Client client) {
        if (client == null) {
            throw new NullValueException("Le client ne peut pas etre null");
        }
        this.client = client;
    }

    public void setLivre(Livre livre) {
        if (livre == null) {
            throw new NullValueException("Le livre ne peut pas etre null");
        }
        this.livre = livre;
    }

    public void setDatetime(LocalDateTime datetime) {
        if (datetime == null) {
            throw new NullValueException("la date ne peut pas etre null");
        } else if (datetime.isAfter(LocalDateTime.now())) {
            throw new IncoherenteDateException("La date de réservation ne peut pas être dans le futur");
        }
        this.datetime = datetime;
    }
    // TODO: CLASSE TEST


    @Override
    public String toString() {
        return "Reservation{" +
                "resId=" + resId +
                ", client=" + client +
                ", livre=" + livre +
                ", datetime=" + datetime +
                '}';
    }
}
