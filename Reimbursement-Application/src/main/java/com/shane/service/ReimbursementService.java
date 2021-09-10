package com.shane.service;

import com.shane.model.AddReimbur;
import com.shane.model.Reimbursement;
import com.shane.model.UpdateReimbur;

import java.util.List;

public interface ReimbursementService {

    void addMyReimbur(AddReimbur addReimbur);
    List<Reimbursement> getAllMyReimbur(Integer userId);
    List<Reimbursement> getOneReimbur(Integer reimburId);
    List<Reimbursement> getAllReimbursement();
    void updateReimburStatus(UpdateReimbur updateReimbur);
}
