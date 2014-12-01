package com.valentin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ListIterator;

import com.valentin.dao.UserDao;
import com.valentin.domain.User;
import com.valentin.service.UserService;
import com.valentin.service.impl.UserServiceImpl;

public class UpdateServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserServiceImpl.newInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter( "id" );
        User user = userService.getUser(Long.valueOf(id));

        String genre = req.getParameter( "genre" );
        String birthday = req.getParameter( "birthday" );
        String message = "";
        User unePersonne = new User();

        // Warning : always protect from unexistant resource
        if(user != null) {
            unePersonne.setNom(id);;
            unePersonne.setGenre(genre);
            unePersonne.setBirthday(birthday);

            req.setAttribute("Personne",unePersonne);
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
            User elem = new User();

            if(user != null) {
                elem.setNom(name);
                elem.setGenre(genre);
                elem.setBirthday(birthday);
                message = "Personne correctement modifi�";
            }
            req.setAttribute("Personne",elem);
            req.setAttribute("message",message);
            this.getServletContext().getRequestDispatcher("/ListPersonne.jsp").forward( req, resp );

        }
    }
}