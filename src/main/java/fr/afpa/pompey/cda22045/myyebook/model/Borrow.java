package fr.afpa.pompey.cda22045.myyebook.model;

public class Borrow {
    private Integer cliId;
    private Integer livId;

    public Borrow() {}

    public Borrow(Integer cliId, Integer livId) {
        setCliId(cliId);
        setLivId(livId);
    }

    public Integer getCliId() {
        return cliId;
    }

    public void setCliId(Integer cliId) {
        if (cliId == null) {
            throw new NullPointerException("cliId ne peut pas être null");
        }
        this.cliId = cliId;
    }

    public Integer getLivId() {
        return livId;
    }

    public void setLivId(Integer livId) {
        if (livId == null) {
            throw new NullPointerException("livId ne peut pas être null");
        }
        this.livId = livId;
    }

    @Override
    public String toString() {
        return "Emprunter [cliId=" + cliId + ", livId=" + livId + "]";
    }
}

