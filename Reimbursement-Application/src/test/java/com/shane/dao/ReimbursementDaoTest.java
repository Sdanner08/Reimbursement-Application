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

class ReimbursementDaoTest {
    @Mock
    private ReimbursementDao reimbursementDao;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
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

        Mockito.when(reimbursementDao.getAllReimbursement()).thenReturn(expectedResult);

        List<Reimbursement> actualResult = reimbursementDao.getAllReimbursement();

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

        Mockito.when(reimbursementDao.getAllMyReimbur(1)).thenReturn(expectedResult);

        List<Reimbursement> actualResult = reimbursementDao.getAllMyReimbur(1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getOneReimbur() {
        Reimbursement reimbursement1 = new Reimbursement(1, 1,null ,null, "food", null, 1, 4, 1,1);

        List<Reimbursement> expectedResult = new ArrayList<>();
        expectedResult.add(reimbursement1);

        Mockito.when(reimbursementDao.getOneReimbur(1)).thenReturn(expectedResult);

        List<Reimbursement> actualResult = reimbursementDao.getOneReimbur(1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addMyReimbur() {
        AddReimbur addReimbur1 = new AddReimbur(19,"redbull", 1,2);

        doNothing().when(reimbursementDao).addMyReimbur(addReimbur1);
        reimbursementDao.addMyReimbur(addReimbur1);

        verify(reimbursementDao, times(1)).addMyReimbur(addReimbur1);

    }

    @Test
    void updateReimburStatus() {
        UpdateReimbur updateReimbur = new UpdateReimbur(1,1,2);

        doNothing().when(reimbursementDao).updateReimburStatus(updateReimbur);
        reimbursementDao.updateReimburStatus(updateReimbur);

        verify(reimbursementDao, times(1)).updateReimburStatus(updateReimbur);

    }
}