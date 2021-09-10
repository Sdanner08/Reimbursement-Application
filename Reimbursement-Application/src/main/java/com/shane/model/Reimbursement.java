package com.shane.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Reimbursement {
    private Integer reimburId;
    private Integer reimburAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date reimburSubmitted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date reimburResolved;
    private String reimburDescription;
    private Byte reimburReceipt;
    private Integer reimburAuthorFk;
    private Integer reimburResolverFk;
    private Integer reimburStatusFk;
    private Integer reimburTypeFk;



    //Constructor
    public void Reimbursement(){}

    public Reimbursement(Integer reimburAmount, String reimburDescription) {
        this.reimburAmount = reimburAmount;
        this.reimburDescription = reimburDescription;
    }

    //IDK why it wouldn't pick this one up when I used only these options.
    public Reimbursement(Integer reimburAmount, String reimburDescription, Integer reimburAuthorFk, Integer reimburTypeFk) {
        this.reimburAmount = reimburAmount;
        this.reimburDescription = reimburDescription;
        this.reimburAuthorFk = reimburAuthorFk;
        this.reimburTypeFk = reimburTypeFk;
    }
    //It was the same with this one so I had to create a model for this as well.
    public Reimbursement(Integer reimburId, Integer reimburResolverFk, Integer reimburStatusFk) {
        this.reimburId = reimburId;
        this.reimburResolverFk = reimburResolverFk;
        this.reimburStatusFk = reimburStatusFk;
    }

    public Reimbursement(Integer reimburId, Integer reimburAmount, Date reimburSubmitted, Date reimburResolved, String reimburDescription, Byte reimburReceipt, Integer reimburAuthorFk, Integer reimburResolverFk, Integer reimburStatusFk, Integer reimburTypeFk) {
        this.reimburId = reimburId;
        this.reimburAmount = reimburAmount;
        this.reimburSubmitted = reimburSubmitted;
        this.reimburResolved = reimburResolved;
        this.reimburDescription = reimburDescription;
        this.reimburReceipt = reimburReceipt;
        this.reimburAuthorFk = reimburAuthorFk;
        this.reimburResolverFk = reimburResolverFk;
        this.reimburStatusFk = reimburStatusFk;
        this.reimburTypeFk = reimburTypeFk;
    }


    //Getters and Setters

    public Integer getReimburId() {
        return reimburId;
    }

    public void setReimburId(Integer reimburId) {
        this.reimburId = reimburId;
    }

    public Integer getReimburAmount() {
        return reimburAmount;
    }

    public void setReimburAmount(Integer reimburAmount) {
        this.reimburAmount = reimburAmount;
    }

    public Date getReimburSubmitted() {
        return reimburSubmitted;
    }

    public void setReimburSubmitted(Date reimburSubmitted) {
        this.reimburSubmitted = reimburSubmitted;
    }

    public Date getReimburResolved() {
        return reimburResolved;
    }

    public void setReimburResolved(Date reimburResolved) {
        this.reimburResolved = reimburResolved;
    }

    public String getReimburDescription() {
        return reimburDescription;
    }

    public void setReimburDescription(String reimburDescription) {
        this.reimburDescription = reimburDescription;
    }

    public Byte getReimburReceipt() {
        return reimburReceipt;
    }

    public void setReimburReceipt(Byte reimburReceipt) {
        this.reimburReceipt = reimburReceipt;
    }

    public Integer getReimburAuthorFk() {
        return reimburAuthorFk;
    }

    public void setReimburAuthorFk(Integer reimburAuthorFk) {
        this.reimburAuthorFk = reimburAuthorFk;
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

    public Integer getReimburTypeFk() {
        return reimburTypeFk;
    }

    public void setReimburTypeFk(Integer reimburTypeFk) {
        this.reimburTypeFk = reimburTypeFk;
    }

    //toString


    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimburId=" + reimburId +
                ", reimburAmount=" + reimburAmount +
                ", reimburSubmitted=" + reimburSubmitted +
                ", reimburResolved=" + reimburResolved +
                ", reimburDescription='" + reimburDescription + '\'' +
                ", reimburReceipt=" + reimburReceipt +
                ", reimburAuthorFk=" + reimburAuthorFk +
                ", reimburResolverFk=" + reimburResolverFk +
                ", reimburStatusFk=" + reimburStatusFk +
                ", reimburTypeFk=" + reimburTypeFk +
                '}';
    }
}
