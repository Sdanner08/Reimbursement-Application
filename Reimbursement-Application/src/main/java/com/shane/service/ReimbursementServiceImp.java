package com.shane.service;

import com.shane.dao.ReimbursementDao;
import com.shane.dao.ReimbursementDaoImp;
import com.shane.model.AddReimbur;
import com.shane.model.Reimbursement;
import com.shane.model.UpdateReimbur;

import java.util.List;

public class ReimbursementServiceImp implements ReimbursementService{
    ReimbursementDao reimbursementDao;

    public ReimbursementServiceImp(){
        reimbursementDao = ReimbursementDaoImp.getInstance();
    }

    @Override
    public List<Reimbursement> getAllMyReimbur(Integer userId) {
        return reimbursementDao.getAllMyReimbur(userId);
    }

    @Override
    public void addMyReimbur(AddReimbur addReimbur) {
        reimbursementDao.addMyReimbur(addReimbur);
    }


    @Override
    public void updateReimburStatus(UpdateReimbur updateReimbur) {
        reimbursementDao.updateReimburStatus( updateReimbur);
    }

    @Override
    public List<Reimbursement> getAllReimbursement() {
        return reimbursementDao.getAllReimbursement();
    }

    @Override
    public List<Reimbursement> getOneReimbur(Integer reimburId) {
        return reimbursementDao.getOneReimbur(reimburId);
    }
}
