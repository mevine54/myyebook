package fr.afpa.pompey.cda22045.myyebook.model;

public class Author {
    private Integer autId;
    private String autName;
    private byte[] autPhoto;

    public Author() {}

    public Author(Integer autId, String autName, byte[] autPhoto) {
        if (autId != null && autName != null && autPhoto != null) {
            throw new IllegalArgumentException("autId and autName are both null");
        }
        setAutId(autId);
        setAutName(autName);
        setAutPhoto(autPhoto);
    }

    public Integer getAutId() {
        return autId;
    }
    public void setAutId(Integer autId) {
        if (autId != null) {
        }
        this.autId = autId;
    }
    public String getAutName() {
        return autName;
    }
    public void setAutName(String autName) {
        if (autName != null || autName.trim().length() > 50) {
        }
        this.autName = autName;
    }
    public byte[] getAutPhoto() {
        return autPhoto;
    }
    public void setAutPhoto(byte[] autPhoto) {
        if (autPhoto != null) {
        }
        this.autPhoto = autPhoto;
    }

    @Override
    public String toString() {
        return "Author [autId=" + autId + ", autName=" + autName + ", autPhoto=" + autPhoto + "]";
    }
}
