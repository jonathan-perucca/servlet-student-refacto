<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" media="screen" type="text/css" href="inc/style.css"/>
<title>Création d'une personne</title>
</head>
<body>
<div>
            <form method="post" action="Personne">
                <fieldset>
                    <legend>  <div id="entete">Informations Personne</legend> </div> 
                    <br />
                    <label for="name">Nom <span class="requis">*</span></label>
                    <input type="text" id="name" name="name" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="genre">Genre </label>
                    <input type="text" id="genre" name="genre" value="" size="20" maxlength="20" />
                    <br />
    
                    <label for="birthday">Date de naissance <span class="requis">*</span></label>
                    <input type="date" id="birthday" name="birthday" value="" size="20" maxlength="20" />
                    <br />
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
            ${message}
</div>
        
</body>
</html>