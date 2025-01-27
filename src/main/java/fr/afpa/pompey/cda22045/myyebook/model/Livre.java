package fr.afpa.pompey.cda22045.myyebook.model;

import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMaximaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMinimaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.NullValueException;
import fr.afpa.pompey.cda22045.myyebook.exception.RegexValidationException;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Livre {
    private Integer id;
    private String titre;
    private String resume;
    private boolean estEnAvant;
    private String image;
    private Auteur auteur;
    private Categorie categorie;

    // Constructeur
    public Livre() {}
//
//    public Livre(Integer id) {
//        setId(id);
//    }

    public Livre(Integer id, String titre, String resume, String image,boolean estEnAvant, Auteur auteur, Categorie categorie) {
        setId(id);
        setTitre(titre);
        setResume(resume);
        setImage(image);
        setAuteur(auteur);
        setCategorie(categorie);
        setEstEnAvant(estEnAvant);
    }


    public void setId(Integer id) {
        if ( id!=null && id <= 0) {
            throw new IllegalArgumentException("L'id ne peut pas etre inferieur ou egal a zero");
        }
        this.id = id;
    }

    public void setTitre(String titre) {
        int longueurMin = 1;
        int longueurMax = 50;
        if (titre == null) {
            throw new IllegalArgumentException("Le titre ne peut pas être null");
        }
        if (titre.length() < longueurMin) {
            throw new LongueurMinimaleException("Le titre est trop court:" + titre + ", " + titre.length() + " caracteres");
        } else if (titre.length() > longueurMax) {
            throw new LongueurMaximaleException("Le titre est trop long:" + titre + ", " + titre.length() + " caracteres");
        }
        this.titre = titre;
    }

    public void setResume(String resume) {
        int longueurMin = 10;
        int longueurMax = 2000;
        if (resume == null) {
            throw new IllegalArgumentException("Le resume ne peut pas être null");
        }
        resume = resume.trim();
        if (resume.length() < longueurMin) {
            throw new LongueurMinimaleException("Le resume est trop court:" + resume + ", " + resume.length() + " caracteres");
        } else if (resume.length() > longueurMax) {
            throw new LongueurMaximaleException("Le resume est trop long:" + resume + ", " + resume.length() + " caracteres");
        }
        this.resume = resume;
    }

    public void setImage(String image) {
        int longueurMin = 6;

        String regex  = ".*[a-zA-Z0-9_àâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ\\-]*\\.(jpg|png|gif|jpeg|bmp)$";
        if (image == null) {
            throw new NullValueException("Le chemin de la couverture ne peut pas etre null");
        } else if (image.length() < longueurMin) {
            throw new LongueurMinimaleException("Le chemin de la couverture est trop court");
        } else if (!image.matches(regex)) {
            throw new RegexValidationException("Le chemin de la couverture n est pas valide .Veuillez choisir un format d'image");
        }
        this.image = image;
    }

    public void setAuteur(Auteur auteur) {
        if (auteur == null) {
            throw new IllegalArgumentException("L'auteur ne peut pas être null");
        }
        this.auteur = auteur;
    }

    public void setCategorie(Categorie categorie) {
        if (categorie == null) {
            throw new IllegalArgumentException("La categorie ne peut pas être null");
        }
        this.categorie = categorie;

    }

    public void setEstEnAvant(boolean estEnAvant) {
        this.estEnAvant = estEnAvant;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", resume='" + resume + '\'' +
                ", image='" + image + '\'' +
                ", auteur=" + auteur +
                ", categorie=" + categorie +
                '}';
    }

}














