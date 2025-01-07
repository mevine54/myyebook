package fr.afpa.pompey.cda22045.myyebook.model;

public class Types {
    private Integer livId;
    private Integer catId;

    public Types() {}

    public Types(Integer livId, Integer catId) {
        setLivId(livId);
        setCatId(catId);
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

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        if (catId == null) {
            throw new NullPointerException("catId ne peut pas être null");
        }
        this.catId = catId;
    }

    @Override
    public String toString() {
        return "Typer [livId=" + livId + ", catId=" + catId + "]";
    }
}

