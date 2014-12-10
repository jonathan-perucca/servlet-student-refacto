package com.valentin.servlet;

import com.valentin.service.UserService;
import com.valentin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SupprimeServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter( "id" );
        String message = "";

        boolean deleted = userService.removeUser(Long.valueOf(id));
        if(deleted) {
            message = "Personne Correctement supprim�";
        } else {
            message = "Personne non enregistrée";
        }

        request.setAttribute("message", message);
        response.sendRedirect("/ListPersonne");
    }
}
