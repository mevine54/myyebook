package fr.afpa.pompey.cda22045.myyebook.model;

public class AdminAccess {
    private Integer libId;
    private Integer secId;

    public AdminAccess() {}

    public AdminAccess(Integer libId, Integer secId) {
        setLibId(libId);
        setSecId(secId);
    }

    public Integer getLibId() {
        return libId;
    }

    public void setLibId(Integer libId) {
        if (libId == null) {
            throw new NullPointerException("libId ne peut pas être null");
        }
        this.libId = libId;
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
        return "AccesAdmin [libId=" + libId + ", secId=" + secId + "]";
    }
}
