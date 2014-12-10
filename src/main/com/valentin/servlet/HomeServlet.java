package com.valentin.servlet;

import com.valentin.domain.User;
import com.valentin.service.UserService;
import com.valentin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserServiceImpl.getInstance();
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String paramAuteur = req.getParameter( "auteur" );
		String message = "Transmission de variables : OK ! " + paramAuteur;

        List<User> users = userService.getUsers();
			
		/* Stockage du message et du bean dans l'objet request */
		req.setAttribute( "test", message );
		req.setAttribute( "listUser", users );
			
		/* Transmission de la paire d'objets request/response � notre JSP */
		
		this.getServletContext().getRequestDispatcher("/index.jsp").forward( req, resp );
	}

}
