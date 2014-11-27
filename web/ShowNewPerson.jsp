<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Création réussie</title>
</head>
<body>
<p><h2> ${message}</h2><p><br>
<p> Voici un Récapitulatif de la nouvelle personne créée<p><br>
		<p>Nom : ${nom }</p><br>
        <p>Genre : ${genre }</p><br>
        <p>Date de Naissance : ${birthday }</p><br>
        <br> <a href="index.jsp">Cliquez ici</a> pour revenir à l'accueil.; 
</body>
</html>