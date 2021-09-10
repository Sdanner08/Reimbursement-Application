package com.shane.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shane.service.ReimbursementService;
import com.shane.model.AddReimbur;
import com.shane.model.Response;
import com.shane.model.UpdateReimbur;
import com.shane.service.ReimbursementServiceImp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;


public class ReimbursementController {
    //setup for Singleton
    private static ReimbursementController reimbursementController;
    ReimbursementService reimbursementService;
    private ReimbursementController(){reimbursementService = new ReimbursementServiceImp();}
    public static ReimbursementController getInstance(){
        if(reimbursementController == null)
            reimbursementController = new ReimbursementController();
        return reimbursementController;
    }

    public void getAllMyReimbur(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        Integer userId = Integer.parseInt(req.getParameter("userId"));

        out.println(new ObjectMapper().writeValueAsString(new Response("Here are your Reimbursements", true, reimbursementService.getAllMyReimbur(userId))));
    }

    public void addMyReimbur(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        AddReimbur addReimbur = new ObjectMapper().readValue(requestBody,AddReimbur.class);

        reimbursementService.addMyReimbur(addReimbur);
        out.println(new ObjectMapper().writeValueAsString(new Response("Reimbursement was added", true, null)));

    }

    public void updateReimburStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        UpdateReimbur updateReimbur = new ObjectMapper().readValue(requestBody,UpdateReimbur.class);

        reimbursementService.updateReimburStatus(updateReimbur);
        out.println(new ObjectMapper().writeValueAsString(new Response("Status has been updated", true, null)));
    }

    public void getOneReimbur(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        Integer reimburId = Integer.parseInt(req.getParameter("reimburId"));

        out.println(new ObjectMapper().writeValueAsString(new Response("Here is the one reimbursement", true, reimbursementService.getOneReimbur(reimburId))));
    }

    public void getAllReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();


        out.println(new ObjectMapper().writeValueAsString(new Response("Here are all the reimbursements", true, reimbursementService.getAllReimbursement())));
    }
}
