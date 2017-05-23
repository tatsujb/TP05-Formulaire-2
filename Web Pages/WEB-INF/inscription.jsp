<%--
    Document   : inscription
    Created on : 18 mai 2017, 09:27:21
    Author     : stag
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <form method="post" action="inscription">
            <fieldset>
                <legend>Inscription</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>


                <label for="nom">Nom d'utilisateur</label>
                <input type="text" id="nom" name="nom" value="<c:out value="${requestScope.utilisateur.nom}"/>" size="20" maxlength="20" />
                <span class="erreur">${requestScope.form.erreurs.nom}</span>
                <br />

                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="text" id="email" name="email" value="<c:out value="${requestScope.utilisateur.email}"/>" size="20" maxlength="60" />
                <span class="erreur">${requestScope.form.erreurs.email}</span>
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${requestScope.form.erreurs.motdepasse}</span>
                <br />

                <label for="confirmation">Confirmation <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <br />


                <input type="submit" value="Inscription" class="sansLabel" />

                <p>Les champs marqués d'un <span class="requis">*</span> sont obligatoires.</p>
                <p class="${(empty requestScope.form.erreurs) ? 'succes' : 'erreur'}">${requestScope.form.resultat}</p>
            </fieldset>
        </form>
    </body>
</html>