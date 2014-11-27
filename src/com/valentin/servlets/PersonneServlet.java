package com.valentin.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;





import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.valentin.beans.Personne;
import com.valentin.services.DAO;


public class PersonneServlet extends HttpServlet {

	String message = "" ;
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
            this.getServletContext().getRequestDispatcher( "/CreatePerson.jsp" ).forward( req, resp );
        } else {
           message = "Personne cr��e avec succ�s !";
           
           int index = DAO.CreationPersonne(nomPersonne, genrePersonne, birthdayPersonne); 			
   			
   			// R�cup�re la personne qui vien d'�tre cr��e
   			Object[] personneCree =DAO.personneCree(index);
   			
   			nomPersonne = (String) personneCree[0]; 
   			genrePersonne = (String) personneCree[1];
   			birthdayPersonne = (String) personneCree[2];
   			String message = (String) personneCree[3];
   			
   			
   		
   		req.setAttribute( "nom", nomPersonne );
   		req.setAttribute( "genre", genrePersonne );
   		req.setAttribute( "birthday", birthdayPersonne );
   		req.setAttribute( "message", message );
   		this.getServletContext().getRequestDispatcher( "/ShowNewPerson.jsp" ).forward( req, resp );
        

		
        }

	}
}
