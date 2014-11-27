package com.valentin.servlets;

import java.io.IOException;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.valentin.beans.Personne;
import com.valentin.services.DAO;

public class SupprimeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String name = req.getParameter( "id" );
		String message = "";
		
		
		ListIterator<Personne> iterator = DAO.mesPersonnesObjet.listIterator();
		while(iterator.hasNext()){
			Personne elem = (Personne) iterator.next();
			boolean elemTrouve = elem.getNom().equals(name);
		    if(elemTrouve ){
		    	iterator.remove();
		    	message = "Personne Correctement supprim�";
		    	req.setAttribute("message",message);
				this.getServletContext().getRequestDispatcher( "/ListPersonne.jsp" ).forward( req, resp );
			
		    }
		                 
		}
	}
}
