package fr.afpa.pompey.cda22045.myyebook.model;

public class Client {
    private Integer cliId;
    private String cliNom;
    private String cliPrenom;
    private String cliEmail;

    public Client() {}

    public Client(Integer cliId, String cliNom, String cliPrenom, String cliEmail) {
        setCliId(cliId);
        setCliNom(cliNom);
        setCliPrenom(cliPrenom);
        setCliEmail(cliEmail);
    }

    public Integer getCliId() {
        return cliId;
    }

    public void setCliId(Integer cliId) {
        if (cliId == null) {
            throw new NullPointerException("cliId ne peut pas être null");
        }
        this.cliId = cliId;
    }

    public String getCliNom() {
        return cliNom;
    }

    public void setCliNom(String cliNom) {
        if (cliNom == null || cliNom.length() > 50) {
            throw new IllegalArgumentException("cliNom ne peut pas être null et doit avoir au maximum 50 caractères");
        }
        this.cliNom = cliNom;
    }

    public String getCliPrenom() {
        return cliPrenom;
    }

    public void setCliPrenom(String cliPrenom) {
        if (cliPrenom == null || cliPrenom.length() > 50) {
            throw new IllegalArgumentException("cliPrenom ne peut pas être null et doit avoir au maximum 50 caractères");
        }
        this.cliPrenom = cliPrenom;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        if (cliEmail == null || cliEmail.length() > 50) {
            throw new IllegalArgumentException("cliEmail ne peut pas être null et doit avoir au maximum 50 caractères");
        }
        this.cliEmail = cliEmail;
    }

    @Override
    public String toString() {
        return "Client [cliId=" + cliId + ", cliNom=" + cliNom + ", cliPrenom=" + cliPrenom + ", cliEmail=" + cliEmail + "]";
    }
}
