package com.shane.controller;

import com.shane.model.Reimbursement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementControllerTest extends Mockito {


    ////I could not get any of my Controller test to work due to Request and Response Issues



    @Mock
    private ReimbursementController reimbursementController;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getInstance() {
    }

    @Test
    void getAllMyReimbur() {
/*
        Reimbursement reimbursement = new Reimbursement(
         1,
        25,
        null,
        null,
        "food",
        null,
        1,
        2,
        3,
        2);
        when(reimbursementController.getAllMyReimbur(Long.valueOf(0L)).thenReturn(reimbursement);
*/
    }

    @Test
    void addMyReimbur() {
    }

    @Test
    void updateReimburStatus() {
    }

    @Test
    void getOneReimbur() {
    }

    @Test
    void getAllReimbursement() {
    }
}