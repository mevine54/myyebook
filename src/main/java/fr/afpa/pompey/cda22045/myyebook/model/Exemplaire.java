package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.IdTropPetitException;
import fr.afpa.pompey.cda22045.myyebook.exception.NullValueException;
import lombok.Getter;

import java.util.List;

@Getter
public class Exemplaire {

    private Integer exemplaireId;
    private Livre livre;

    public Exemplaire() {
    }

    public Exemplaire(Integer exemplaireId, Livre livre) {
        setExemplaireId(exemplaireId);
        setLivre(livre);
    }

    public void setExemplaireId(Integer exemplaireId) {
        if (exemplaireId != null && exemplaireId <= 0) {
            throw new IdTropPetitException("L'id ne peut pas etre inferieur ou egal a zero");
        }
        this.exemplaireId = exemplaireId;
    }

    public void setLivre(Livre livre) {
        if (livre == null) {
            throw new NullValueException("Le livre ne peut pas etre null");
        }
        this.livre = livre;
    }

    // TODO: CLASSE TEST

}
