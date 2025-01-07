package fr.afpa.pompey.cda22045.myyebook.model;

public class ClientAccess {
    private Integer cliId;
    private Integer secId;

    public ClientAccess() {}

    public ClientAccess(Integer cliId, Integer secId) {
        setCliId(cliId);
        setSecId(secId);
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

    public Integer getSecId() {
        return secId;
    }

    public void setSecId(Integer secId) {
        if (secId == null) {
            throw new NullPointerException("secId ne peut pas être null");
        }
        this.secId = secId;
    }

    @Override
    public String toString() {
        return "AccesClient [cliId=" + cliId + ", secId=" + secId + "]";
    }
}

