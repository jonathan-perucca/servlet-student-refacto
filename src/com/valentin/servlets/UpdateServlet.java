package com.valentin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ListIterator;

import com.valentin.beans.Personne;
import com.valentin.services.DAO;

public class UpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String id = req.getParameter( "id" );
		String genre = req.getParameter( "genre" );
		String birthday = req.getParameter( "birthday" );
		String message = "";
		Personne unePersonne = new Personne();
		
		
		ListIterator<Personne> iterator = DAO.mesPersonnesObjet.listIterator();
		
		
		
		while(iterator.hasNext()){
			Personne elem = (Personne) iterator.next();
			boolean elemTrouve = elem.getNom().equals(id);
		    if(elemTrouve){

		   		unePersonne.setNom(id);;
		   		unePersonne.setGenre(genre);
		   		unePersonne.setBirthday(birthday);
		   		
		    	req.setAttribute("Personne",unePersonne);
				this.getServletContext().getRequestDispatcher( "/UpdatePersonne.jsp" ).forward( req, resp );
			
		    }
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
	            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( req, resp );
	        }else {
		
		ListIterator<Personne> iterator = DAO.mesPersonnesObjet.listIterator();
		       
		
		while(iterator.hasNext()){
			Personne elem = (Personne) iterator.next();
			boolean elemTrouve = elem.getNom().equals(id);
		    if(elemTrouve){

		    	elem.setNom(name);;
		    	elem.setGenre(genre);
		    	elem.setBirthday(birthday);
		   		message = "Personne correctement modifi�";
		   		
		    	
		    }
		    req.setAttribute("Personne",elem);
		    req.setAttribute("message",message);
			this.getServletContext().getRequestDispatcher( "/ListPersonne.jsp" ).forward( req, resp );
		
		
		}
		}
	}
}
