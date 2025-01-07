package fr.afpa.pompey.cda22045.myyebook.model;

public class Libraire {
    private Integer libId;
    private String libNom;
    private String libPrenom;

    public Libraire() {}

    public Libraire(Integer libId, String libNom, String libPrenom) {
        setLibId(libId);
        setLibNom(libNom);
        setLibPrenom(libPrenom);
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

    public String getLibNom() {
        return libNom;
    }

    public void setLibNom(String libNom) {
        if (libNom == null || libNom.length() > 50) {
            throw new IllegalArgumentException("libNom ne peut pas être null et doit avoir au maximum 50 caractères");
        }
        this.libNom = libNom;
    }

    public String getLibPrenom() {
        return libPrenom;
    }

    public void setLibPrenom(String libPrenom) {
        if (libPrenom == null || libPrenom.length() > 50) {
            throw new IllegalArgumentException("libPrenom ne peut pas être null et doit avoir au maximum 50 caractères");
        }
        this.libPrenom = libPrenom;
    }

    @Override
    public String toString() {
        return "Libraire [libId=" + libId + ", libNom=" + libNom + ", libPrenom=" + libPrenom + "]";
    }
}
