package com.valentin.servlet;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.valentin.domain.User;
import com.valentin.service.UserService;
import com.valentin.service.impl.UserServiceImpl;


public class PersonneServlet extends HttpServlet {

    private String message;

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserServiceImpl.getInstance();
        message = "";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getUsers();
        request.setAttribute("listUser", users);
        getServletContext().getRequestDispatcher("/ListPersonne.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		/* Initialisation de ses propri�t�s */
        String nomPersonne = request.getParameter( "name" );
        String genrePersonne = request.getParameter( "genre" );
        String birthdayPersonne = request.getParameter( "birthday" );
		
        /*
         * Initialisation du message � afficher : si un des champs obligatoires
         * du formulaire n'est pas renseign�.
         */

        if ( nomPersonne.trim().isEmpty() || genrePersonne.trim().isEmpty() || birthdayPersonne.trim().isEmpty()) {
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires.";
            request.setAttribute("message", message);
            this.getServletContext().getRequestDispatcher("/CreatePerson.jsp").forward( request, response );
        } else {
            message = "Personne cr��e avec succ�s !";

            User user = userService.saveUser(nomPersonne, genrePersonne, birthdayPersonne);

            response.setStatus(HttpServletResponse.SC_SEE_OTHER);
            response.sendRedirect("/ListPersonne");
//            this.getServletContext().getRequestDispatcher("/ShowNewPerson.jsp").forward( request, response );
        }
    }
}
