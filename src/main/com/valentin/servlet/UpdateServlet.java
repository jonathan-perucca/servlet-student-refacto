package com.valentin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.valentin.domain.User;
import com.valentin.service.UserService;
import com.valentin.service.impl.UserServiceImpl;

public class UpdateServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter( "id" );
        User user = userService.getUser(Long.valueOf(id));

        // Warning : always protect from unexistant resource
        if(user != null) {
            req.setAttribute("user", user);
            this.getServletContext().getRequestDispatcher("/UpdatePersonne.jsp").forward( req, resp );
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String id = req.getParameter( "id" );
        String name = req.getParameter( "name" );
        String genre = req.getParameter( "genre" );
        String birthday = req.getParameter( "birthday" );
        String message = "";

        if ( name.trim().isEmpty() || genre.trim().isEmpty() || birthday.trim().isEmpty()) {
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. Veuillez Recommencer";
            req.setAttribute( "message", message );
            this.getServletContext().getRequestDispatcher("/index.jsp").forward( req, resp );
        }else {
            User user = userService.getUser(Long.valueOf(id));
            if(user != null) {
                user.setNom(name);
                user.setGenre(genre);
                user.setBirthday(birthday);
                user = userService.saveUser(user);

                message = "Personne correctement modifi�";
            }
            req.setAttribute("user", user);
            req.setAttribute("message", message);

            resp.setStatus(HttpServletResponse.SC_SEE_OTHER);
            resp.sendRedirect("/ListPersonne");
//            this.getServletContext().getRequestDispatcher("/ListPersonne").forward( req, resp );

        }
    }
}