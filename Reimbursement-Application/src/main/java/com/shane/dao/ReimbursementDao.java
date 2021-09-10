package com.shane.dao;

import com.shane.model.AddReimbur;
import com.shane.model.Reimbursement;
import com.shane.model.UpdateReimbur;


import java.util.List;

public interface ReimbursementDao {

    List<Reimbursement> getAllReimbursement();
    List<Reimbursement> getAllMyReimbur(Integer userId);
    List<Reimbursement> getOneReimbur(Integer reimburId);

    void addMyReimbur(AddReimbur addReimbur);
    void updateReimburStatus(UpdateReimbur updateReimbur);

}
