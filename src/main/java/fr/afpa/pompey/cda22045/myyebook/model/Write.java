package fr.afpa.pompey.cda22045.myyebook.model;

public class Write {
    private Integer autId;
    private Integer livId;

    public Write() {}

    public Write(Integer autId, Integer livId) {
        setAutId(autId);
        setLivId(livId);
    }

    public Integer getAutId() {
        return autId;
    }

    public void setAutId(Integer autId) {
        if (autId == null) {
            throw new NullPointerException("autId ne peut pas être null");
        }
        this.autId = autId;
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
        return "Ecrire [autId=" + autId + ", livId=" + livId + "]";
    }
}

