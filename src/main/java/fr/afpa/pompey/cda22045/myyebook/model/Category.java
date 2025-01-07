package fr.afpa.pompey.cda22045.myyebook.model;

public class Category {
    private Integer catId;
    private String catLibelle;

    public Category() {}

    public Category(Integer catId, String catLibelle) {
        setCatId(catId);
        setCatLibelle(catLibelle);
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        if (catId == null) {
            throw new NullPointerException("catId ne peut pas être null");
        }
        this.catId = catId;
    }

    public String getCatLibelle() {
        return catLibelle;
    }

    public void setCatLibelle(String catLibelle) {
        if (catLibelle == null || catLibelle.length() > 50) {
            throw new IllegalArgumentException("catLibelle ne peut pas être null et doit avoir au maximum 50 caractères");
        }
        this.catLibelle = catLibelle;
    }

    @Override
    public String toString() {
        return "Categorie [catId=" + catId + ", catLibelle=" + catLibelle + "]";
    }
}
