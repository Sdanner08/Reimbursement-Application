package com.shane.model;


/*////////////////////////////////////
    I was forced to create this model. The program wasn't picking up my constructor I had for it.
*/////////////////////////////////////



public class UpdateReimbur {
    private Integer reimburId;
    private Integer reimburResolverFk;
    private Integer reimburStatusFk;


    public UpdateReimbur() {
    }

    public UpdateReimbur(Integer reimburId, Integer reimburResolverFk, Integer reimburStatusFk) {
        this.reimburId = reimburId;
        this.reimburResolverFk = reimburResolverFk;
        this.reimburStatusFk = reimburStatusFk;
    }

    public Integer getReimburId() {
        return reimburId;
    }

    public void setReimburId(Integer reimburId) {
        this.reimburId = reimburId;
    }

    public Integer getReimburResolverFk() {
        return reimburResolverFk;
    }

    public void setReimburResolverFk(Integer reimburResolverFk) {
        this.reimburResolverFk = reimburResolverFk;
    }

    public Integer getReimburStatusFk() {
        return reimburStatusFk;
    }

    public void setReimburStatusFk(Integer reimburStatusFk) {
        this.reimburStatusFk = reimburStatusFk;
    }

    @Override
    public String toString() {
        return "UpdateReimbur{" +
                "reimburId=" + reimburId +
                ", reimburResolverFk=" + reimburResolverFk +
                ", reimburStatusFk=" + reimburStatusFk +
                '}';
    }
}
