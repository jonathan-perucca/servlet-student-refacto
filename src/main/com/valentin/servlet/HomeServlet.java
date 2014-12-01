package com.valentin.servlet;

import com.valentin.dao.UserDao;
import com.valentin.service.UserService;
import com.valentin.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserServiceImpl.newInstance();
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String paramAuteur = req.getParameter( "auteur" );
		String message = "Transmission de variables : OK ! " + paramAuteur;
		req.setAttribute( "test", message );
		
		/* Cr�ation de la liste et insertion de quatre �l�ments */
		List<Integer> listePersonne = new ArrayList<Integer>();
		listePersonne.add( 27 );
		listePersonne.add( 12 );
		listePersonne.add( 138 );
		listePersonne.add( 6 );
			
			
		/* Stockage du message et du bean dans l'objet request */
		req.setAttribute( "test", message );
		req.setAttribute( "liste", listePersonne );
			
		/* Transmission de la paire d'objets request/response � notre JSP */
		
		this.getServletContext().getRequestDispatcher("/index.jsp").forward( req, resp );
	}

}
