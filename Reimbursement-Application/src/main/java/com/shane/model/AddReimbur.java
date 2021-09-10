package com.shane.model;

/*////////////////////////////////////
    I was forced to create this model. The program wasn't picking up my constructor I had for it.
*/////////////////////////////////////



public class AddReimbur {
    private Integer reimburAmount;
    private String reimburDescription;
    private Integer reimburAuthorFk;
    private Integer reimburTypeFk;


    public AddReimbur() {
    }

    public AddReimbur(Integer reimburAmount, String reimburDescription, Integer reimburAuthorFk, Integer reimburTypeFk) {
        this.reimburAmount = reimburAmount;
        this.reimburDescription = reimburDescription;
        this.reimburAuthorFk = reimburAuthorFk;
        this.reimburTypeFk = reimburTypeFk;
    }

    public Integer getReimburAmount() {
        return reimburAmount;
    }

    public void setReimburAmount(Integer reimburAmount) {
        this.reimburAmount = reimburAmount;
    }

    public String getReimburDescription() {
        return reimburDescription;
    }

    public void setReimburDescription(String reimburDescription) {
        this.reimburDescription = reimburDescription;
    }

    public Integer getReimburAuthorFk() {
        return reimburAuthorFk;
    }

    public void setReimburAuthorFk(Integer reimburAuthorFk) {
        this.reimburAuthorFk = reimburAuthorFk;
    }

    public Integer getReimburTypeFk() {
        return reimburTypeFk;
    }

    public void setReimburTypeFk(Integer reimburTypeFk) {
        this.reimburTypeFk = reimburTypeFk;
    }

    @Override
    public String toString() {
        return "AddReimbur{" +
                "reimburAmount=" + reimburAmount +
                ", reimburDescription='" + reimburDescription + '\'' +
                ", reimburAuthorFk=" + reimburAuthorFk +
                ", reimburTypeFk=" + reimburTypeFk +
                '}';
    }
}
