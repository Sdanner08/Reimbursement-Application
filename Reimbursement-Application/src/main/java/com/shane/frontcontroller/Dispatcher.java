package com.shane.frontcontroller;

import com.shane.controller.ReimbursementController;
import com.shane.controller.UserController;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//endpoint routing
@WebServlet(name="dispatcher", urlPatterns = "/api/*")
public class Dispatcher extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String URI= req.getRequestURI();
        System.out.println(URI);
        switch(URI){
            case "/api/login":
                if(req.getMethod().equals("POST"))
                    UserController.getInstance().login(req, resp);
                break;
            case "/api/addUser":
                if(req.getMethod().equals("POST"))
                    UserController.getInstance().addUser(req, resp);
                break;
            case "/api/getAllUsers":
                if(req.getMethod().equals("GET"))
                    UserController.getInstance().getAllUsers(req, resp);
                break;
            case "/api/reimbursement":
                if(req.getMethod().equals("POST"))
                ReimbursementController.getInstance().addMyReimbur(req, resp);
                break;
            case "/api/allReimbur":
                if(req.getMethod().equals("GET"))
                ReimbursementController.getInstance().getAllMyReimbur(req, resp);
                break;
            case "/api/oneReimbur":
                if(req.getMethod().equals("GET"))
                ReimbursementController.getInstance().getOneReimbur(req, resp);
                break;
            case"/api/finance/reimbursement":
                switch(req.getMethod()){
                    case "GET":
                        ReimbursementController.getInstance().getAllReimbursement(req, resp);
                        break;
                    case "PATCH":
                        ReimbursementController.getInstance().updateReimburStatus(req, resp);
                        break;
                }
                break;
            case "/api/check-session":
                if(req.getMethod().equals("GET"))
                    UserController.getInstance().checkSession(req, resp);
                break;
            case "/api/logout":
                if(req.getMethod().equals("GET"))
                    UserController.getInstance().logOut(req, resp);
        }
    }
}
