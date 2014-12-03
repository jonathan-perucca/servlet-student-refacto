<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ page import="com.valentin.domain.User" %>
<%@ page import="java.util.List" %>


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
         List<User> listUser = (List<User>) request.getAttribute("listUser");
        if(listUser != null) {
            for(User user : listUser) { %>
            <tr>
                <td><%= user.getNom() %></td>
                <td><%= user.getGenre() %></td>
                <td><%= user.getBirthday() %></td>
                <td>
                    <a href="Supprime?id=<%= user.getId() %>">Supprimer </a> &nbsp;/
                    <a href="Update?id=<%= user.getId() %>&genre=<%= user.getGenre() %>&birthday=<%= user.getBirthday() %>">Modifier</a>
                </td>
            </tr>
            <% }
        } %>

</table>
${message}
</body>
</html>