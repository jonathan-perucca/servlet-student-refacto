<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.valentin.services.DAO" %>
    <%@ page import="com.valentin.beans.Personne" %>
    <%@ page import="com.valentin.servlets.PersonneServlet" %>
    <%@ page import="java.util.Iterator" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" media="screen" type="text/css" href="inc/style.css"/>
<title>Liste des personnes créées</title>
</head>
<body>
<br> <a href="CreatePerson.jsp">Créer une nouvelle personne</a>
<br> <a href="index.jsp">Accueil</a>   
<table>
<caption>Personne(s) que vous avez crée(s)</caption>
	<tr>
    <th>Nom</th>
    <th>Genre</th> 
    <th>Date de naissance</th>
	<th>Actions</th>  
 	</tr>
<%
int nbPersonne = DAO.mesPersonnesObjet.size();
int i = 0;
String nom = "";
String genre = "";
String birthday = "";
if ( nbPersonne > 0) 
{ 
for (i = 0; i < nbPersonne; i++){
	
	 birthday = DAO.mesPersonnesObjet.get(i).getBirthday();
	 genre = DAO.mesPersonnesObjet.get(i).getGenre();
	 nom = DAO.mesPersonnesObjet.get(i).getNom();
	
	 
 %>
	
	<tr>
	<td><%=nom%></td>
	<td><%=genre%></td>
	<td><%=birthday%></td>
	<td><a href="Supprime?id=<%=nom%>">Supprimer </a> &nbsp;/ <a href="Update?id=<%=nom%>&genre=<%=genre%>&birthday=<%=birthday%>">Modifier</a> 
	</tr> 
<%}} %> 

</table>
${message}
</body>
</html>