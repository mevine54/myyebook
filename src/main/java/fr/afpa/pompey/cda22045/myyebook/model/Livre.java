package fr.afpa.pompey.cda22045.myyebook.model;

public class Livre {
    private Integer livId;
    private String livTitle;
    private String livSummary;
    private byte[] livImage;
    private int livQuantity;

    // Constructeur
    public Livre() {}

    public Livre(Integer livId, String livTitle, String livSummary, byte[] livImage, int livQuantity) {
        if (livTitle == null || livSummary == null || livQuantity < 0) {
            throw new IllegalArgumentException("Données invalides");
        }
        setLivId(livId);
        setLivTitle(livTitle);
        setLivSummary(livSummary);
        setLivImage(livImage);
        setLivQuantity(livQuantity);
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

    public String getLivTitle() {
        return livTitle;
    }

    public void setLivTitle(String livTitle) {
        if (livTitle == null) {
            throw new IllegalArgumentException("Le titre ne peut pas être null");
        }
        this.livTitle = livTitle;
    }

    public String getLivSummary() {
        return livSummary;
    }

    public void setLivSummary(String livSummary) {
        if (livSummary == null) {
            throw new IllegalArgumentException("Le résumé ne peut pas être null");
        }
        this.livSummary = livSummary;
    }

    public byte[] getLivImage() {
        return livImage;
    }

    public void setLivImage(byte[] livImage) {
        this.livImage = livImage;
    }

    public int getLivQuantity() {
        return livQuantity;
    }

    public void setLivQuantity(int livQuantity) {
        if (livQuantity < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative");
        }
        this.livQuantity = livQuantity;
    }

    @Override
    public String toString() {
        return "Book [title=" + livTitle + ", summary=" + livSummary + ", image=" + (livImage != null ? "Image présente" : "Pas d'image") + ", quantity=" + livQuantity + "]";
    }
}

