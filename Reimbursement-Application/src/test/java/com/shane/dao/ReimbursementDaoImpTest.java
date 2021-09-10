package com.shane.dao;

import com.shane.model.AddReimbur;
import com.shane.model.Reimbursement;
import com.shane.model.UpdateReimbur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReimbursementDaoImpTest {
    @Mock
    private ReimbursementDaoImp reimbursementDaoImp;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getInstance() {
    }

    @Test
    void getAllReimbursement() {
        Reimbursement reimbursement1 = new Reimbursement(1, 1,null ,null, "food", null, 1, 4, 1,1);
        Reimbursement reimbursement2 = new Reimbursement(2, 34,null ,null, "hotel", null, 2, 5, 2,2);
        Reimbursement reimbursement3 = new Reimbursement(3, 356,null ,null, "travel", null, 3, 6, 3,3);

        List<Reimbursement> expectedResult = new ArrayList<>();
        expectedResult.add(reimbursement1);
        expectedResult.add(reimbursement2);
        expectedResult.add(reimbursement3);

        Mockito.when(reimbursementDaoImp.getAllReimbursement()).thenReturn(expectedResult);

        List<Reimbursement> actualResult = reimbursementDaoImp.getAllReimbursement();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAllMyReimbur() {
        Reimbursement reimbursement1 = new Reimbursement(1, 1,null ,null, "food", null, 1, 4, 1,1);
        Reimbursement reimbursement2 = new Reimbursement(2, 34,null ,null, "hotel", null, 1, 5, 2,2);
        Reimbursement reimbursement3 = new Reimbursement(3, 356,null ,null, "travel", null, 1, 6, 3,3);

        List<Reimbursement> expectedResult = new ArrayList<>();
        expectedResult.add(reimbursement1);
        expectedResult.add(reimbursement2);
        expectedResult.add(reimbursement3);

        Mockito.when(reimbursementDaoImp.getAllMyReimbur(1)).thenReturn(expectedResult);

        List<Reimbursement> actualResult = reimbursementDaoImp.getAllMyReimbur(1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addMyReimbur() {
        AddReimbur addReimbur1 = new AddReimbur(19,"redbull", 1,2);

        doNothing().when(reimbursementDaoImp).addMyReimbur(addReimbur1);
        reimbursementDaoImp.addMyReimbur(addReimbur1);

        verify(reimbursementDaoImp, times(1)).addMyReimbur(addReimbur1);

    }

    @Test
    void getOneReimbur() {
        Reimbursement reimbursement1 = new Reimbursement(1, 1,null ,null, "food", null, 1, 4, 1,1);

        List<Reimbursement> expectedResult = new ArrayList<>();
        expectedResult.add(reimbursement1);

        Mockito.when(reimbursementDaoImp.getOneReimbur(1)).thenReturn(expectedResult);

        List<Reimbursement> actualResult = reimbursementDaoImp.getOneReimbur(1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void updateReimburStatus() {
        UpdateReimbur updateReimbur = new UpdateReimbur(1,1,2);

        doNothing().when(reimbursementDaoImp).updateReimburStatus(updateReimbur);
        reimbursementDaoImp.updateReimburStatus(updateReimbur);

        verify(reimbursementDaoImp, times(1)).updateReimburStatus(updateReimbur);

    }
}