package fr.afpa.pompey.cda22045.myyebook.model;

public class Livre {
    private Integer livId;
    private String livTitre;
    private String livResume;
    private byte[] livImage;
    private int livQuantite;

    // Constructeur
    public Livre() {}

    public Livre(Integer livId, String livTitre, String livResume, byte[] livImage, int livQuantite) {
        if (livTitre == null || livResume == null || livQuantite < 0) {
            throw new IllegalArgumentException("Données invalides");
        }
        setLivId(livId);
        setLivTitre(livTitre);
        setLivResume(livResume);
        setLivImage(livImage);
        setLivQuantite(livQuantite);
    }

    public Integer getLivId() {
        return livId;
    }

    public void setLivId(Integer livId) {
        if (livId == null) {
            throw new IllegalArgumentException("L'id ne peut être vide");
        }
        this.livId = livId;
    }

    public String getLivTitre() {
        return livTitre;
    }

    public void setLivTitre(String livTitre) {
        if (livTitre == null) {
            throw new IllegalArgumentException("Le titre ne peut pas être null");
        }
        this.livTitre = livTitre;
    }

    public String getLivResume() {
        return livResume;
    }

    public void setLivResume(String livResume) {
        if (livResume == null) {
            throw new IllegalArgumentException("Le résumé ne peut pas être null");
        }
        this.livResume = livResume;
    }

    public byte[] getLivImage() {
        return livImage;
    }

    public void setLivImage(byte[] livImage) {
        this.livImage = livImage;
    }

    public int getLivQuantite() {
        return livQuantite;
    }

    public void setLivQuantite(int livQuantite) {
        if (livQuantite < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative");
        }
        this.livQuantite = livQuantite;
    }

    @Override
    public String toString() {
        return "Book [title=" + livTitre + ", summary=" + livResume + ", image=" + (livImage != null ? "Image présente" : "Pas d'image") + ", quantity=" + livQuantite + "]";
    }
}

