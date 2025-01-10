package fr.afpa.pompey.cda22045.myyebook.model;

public class Livre {
    private Integer id;
    private String titre;
    private String resume;
    private String image;
    private int quantite;

    // Constructeur
    public Livre() {}

    public Livre(Integer id, String titre, String resume, String image, int quantite) {

    }

    public void setId(Integer id) {
        if ( id <= 1) {
            throw new IllegalArgumentException("L'id ne peut pas etre inferieur ou egal a zero");
        }
        this.id = id;
    }

    public void setTitre(String titre) {
        if (titre == null) {
            throw new IllegalArgumentException("Le titre ne peut pas être null");
        }
        this.titre = titre;
    }

    public void setResume(String resume) {
        if (resume == null) {
            throw new IllegalArgumentException("Le resume ne peut pas être null");
        }
        this.resume = resume;
    }

    public void setImage(String image) {
        if (image == null) {
            throw new IllegalArgumentException("Le chemin de la photo de l'auteur ne peut pas etre null");
        } else if (image.length() < 5) {
            throw new IllegalArgumentException("Le chemin de la photo de l'auteur est trop court");
        }
        this.image = image;
    }

    public void setQuantite(int quantite) {
        if (quantite < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative");
        }
        this.quantite = quantite;
    }


}














