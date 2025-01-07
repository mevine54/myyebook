package fr.afpa.pompey.cda22045.myyebook.model;

public class Account {
    private Integer secId;
    private String secLogin;
    private String secPass;

    public Account() {}

    public Account(Integer secId, String secLogin, String secPass) {
        setSecId(secId);
        setSecLogin(secLogin);
        setSecPass(secPass);
    }

    public Integer getSecId() {
        return secId;
    }

    public void setSecId(Integer secId) {
        if (secId == null) {
            throw new NullPointerException("secId ne peut pas être null");
        }
        this.secId = secId;
    }

    public String getSecLogin() {
        return secLogin;
    }

    public void setSecLogin(String secLogin) {
        if (secLogin == null || secLogin.length() > 10) {
            throw new IllegalArgumentException("secLogin ne peut pas être null et doit avoir au maximum 10 caractères");
        }
        this.secLogin = secLogin;
    }

    public String getSecPass() {
        return secPass;
    }

    public void setSecPass(String secPass) {
        if (secPass == null || secPass.length() > 255) {
            throw new IllegalArgumentException("secPass ne peut pas être null et doit avoir au maximum 255 caractères");
        }
        this.secPass = secPass;
    }

    @Override
    public String toString() {
        return "Compte [secId=" + secId + ", secLogin=" + secLogin + ", secPass=" + (secPass != null ? "********" : "null") + "]";
    }
}
