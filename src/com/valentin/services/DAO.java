package com.valentin.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.valentin.beans.Personne;

public class DAO {
	public static ArrayList<String> mesPersonnes = new ArrayList();
	public static ArrayList<Personne> mesPersonnesObjet = new ArrayList<Personne>();
	static int id = 0 ;
	
	public static Object[] personneCree(int index){
		
		int i = index;
		int nbPersonnes = mesPersonnesObjet.size();
		String nom = null;
		String genre = null;
		String birthday = null;
		String message = "";
		
		if (nbPersonnes > 0){
			
				nom = mesPersonnesObjet.get(i).getNom();
				genre = mesPersonnesObjet.get(i).getGenre();
				birthday = mesPersonnesObjet.get(i).getBirthday();
				
		
		}
		else{
			
			message = "Erreur syst�me : la derni�re personne n'a pas pu �tre r�cup�r�e";
			
		}
		
		Object[] personneCree = { nom , 	genre, birthday , message }; 
		return personneCree; 
		  
			
		
	
	}

	public static int CreationPersonne(String nom, String genre, String Birthday){
		
		int index;
		
		id ++;
	    /* Cr�ation de la personne */
   		Personne unePersonne = new Personne();
   		
   		unePersonne.setId(id);
   		unePersonne.setNom( nom );
   		unePersonne.setGenre(genre );
   		unePersonne.setBirthday(Birthday);
   		
   		mesPersonnesObjet.add(unePersonne);
   		
   		 index =DAO.mesPersonnesObjet.indexOf(unePersonne);
		
   		return index;
		
		
	}

}