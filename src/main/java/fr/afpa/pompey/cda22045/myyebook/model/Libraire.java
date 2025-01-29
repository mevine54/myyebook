package fr.afpa.pompey.cda22045.myyebook.model;


import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMaximaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.LongueurMinimaleException;
import fr.afpa.pompey.cda22045.myyebook.exception.NullValueException;
import fr.afpa.pompey.cda22045.myyebook.exception.RegexValidationException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Libraire  {
    private Compte compte;
    private Integer libId;
    private String nom;
    private String prenom;
    private boolean estApprouve;

    public Libraire() {
    }

    public Libraire(Compte compte,Integer libId, boolean estApprouve  ,String nom, String prenom) {
        setCompte(compte);
        if (estApprouve){
            compte.setRole("ROLE_LIBRAIRE");
        }else{
            compte.setRole("ROLE_LIBRAIRE_ATTENTE");
        }
        setEstApprouve(estApprouve);
        setNom(nom);
        setPrenom(prenom);
    }

    private void setCompte(Compte compte) {
        if (compte == null) {
            throw new IllegalArgumentException("compte is null");
        }  else if ( compte.getRole().equals("ROLE_LIBRAIRE") || compte.getRole().equals("ROLE_LIBRAIRE_ATTENTE")   ) {
            this.compte = compte;
            log.info(compte.getRole());
        }else{
            throw new IllegalArgumentException("Ce compte n'appartient pas a une libraire");
        }
    }



    public void setLibId(Integer libId) {
        if ( libId != null &&  libId <= 0) {
            throw new IllegalArgumentException("L'id ne peut pas etre inferieur ou egal a zero");

        }
        this.libId = libId;
    }


    public void setNom(String nom) {
        int longueurMin = 2;
        int longueurMax = 50;
        if (nom == null) {
            throw new NullValueException("Le nom de la libraire ne peut pas etre null");
        }
        if (nom == null) {
            throw new NullValueException("Le nom de la libraire ne peut pas etre null");
        }


        nom = nom.trim();
        String regex = "^[A-Za-zàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ\\-\\s]{" + longueurMin + "," + longueurMax + "}$";
        if (nom.length() < longueurMin) {
            throw new LongueurMinimaleException("Le nom de la libraire est trop court:" + nom + ", " + nom.length() + " caracteres");
        } else if (nom.length() > longueurMax) {
            throw new LongueurMaximaleException("Le nom de la libraire est trop long:" + nom + ", " + nom.length() + " caracteres");
        } else if (!nom.matches(regex)) {
            throw new RegexValidationException("Le nom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de " + longueurMin + " à " + longueurMax + " caractères");
        }
        this.nom = nom;
    }

    public void setPrenom(String prenom) {

        int longueurMin = 2;
        int longueurMax = 50;
        if (prenom == null) {
            throw new NullValueException("Le prenom de la libraire ne peut pas etre null");
        }
        prenom = prenom.trim();
        String regex = "^[A-Za-zàâäéèêëîïôöùûüçÀÂÄÉÈÊËÎÏÔÖÙÛÜÇ\\-]{" + longueurMin + "," + longueurMax + "}$";
        if (prenom.length() < longueurMin) {
            throw new LongueurMinimaleException("Le mot de passe doit avoir au minimum " + longueurMin + " caractères");
        } else if (prenom.length() > longueurMax) {
            throw new LongueurMaximaleException("Le mot de passe doit avoir au maximum " + longueurMax + " caractères");
        } else if (!prenom.matches(regex)) {
            throw new RegexValidationException("Le prenom n'est pas valide. Veuillez entrer un nom contenant uniquement des lettres et des espaces, avec une longueur de " + longueurMin + " à " + longueurMax + " caractères");
        }
        this.prenom = prenom;
    }

    public void setEstApprouve(boolean estApprouve) {
        if (estApprouve){
            compte.setRole("ROLE_LIBRAIRE");
        }else{
            compte.setRole("ROLE_LIBRAIRE_ATTENTE");
        }
        this.estApprouve = estApprouve;
    }

    @Override
    public String toString() {
        return "Libraire{" +
                "compte=" + compte +
                ", libId=" + libId +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", estApprouve=" + estApprouve +
                '}';
    }
}
