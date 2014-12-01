package com.valentin.servlet;

import java.io.IOException;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.valentin.domain.User;
import com.valentin.service.UserService;
import com.valentin.service.impl.UserServiceImpl;

public class SupprimeServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserServiceImpl.newInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter( "id" );
        String message = "";

        boolean deleted = userService.removeUser(Long.valueOf(id));
        if(deleted) {
            message = "Personne Correctement supprim�";
        } else {
            message = "Personne non enregistrée";
        }

        req.setAttribute("message",message);
        this.getServletContext().getRequestDispatcher("/ListPersonne.jsp").forward( req, resp );
    }
}
