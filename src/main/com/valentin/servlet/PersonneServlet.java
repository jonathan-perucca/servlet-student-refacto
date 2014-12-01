package com.valentin.servlet;

import java.io.IOException;


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
        userService = UserServiceImpl.newInstance();
        message = "";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

		/* Initialisation de ses propri�t�s */
        String nomPersonne = req.getParameter( "name" );
        String genrePersonne = req.getParameter( "genre" );
        String birthdayPersonne = req.getParameter( "birthday" );
		
        /*
         * Initialisation du message � afficher : si un des champs obligatoires
         * du formulaire n'est pas renseign�.
         */

        if ( nomPersonne.trim().isEmpty() || genrePersonne.trim().isEmpty() || birthdayPersonne.trim().isEmpty()) {
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires.";
            req.setAttribute( "message", message );
            this.getServletContext().getRequestDispatcher("/CreatePerson.jsp").forward( req, resp );
        } else {
            message = "Personne cr��e avec succ�s !";

            User user = userService.saveUser(nomPersonne, genrePersonne, birthdayPersonne);

            req.setAttribute( "nom", user.getNom() );
            req.setAttribute( "genre", user.getGenre() );
            req.setAttribute( "birthday", user.getBirthday() );
            req.setAttribute( "message", message );
            this.getServletContext().getRequestDispatcher("/ShowNewPerson.jsp").forward( req, resp );
        }
    }
}
