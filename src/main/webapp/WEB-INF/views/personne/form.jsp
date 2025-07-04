<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter une Personne</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">Ajouter une Personne</h1>
        
        <form action="/biblio/personnes/save" method="post">
            <div class="mb-3">
                <label for="nom" class="form-label">Nom</label>
                <input type="text" class="form-control" id="nom" name="nom" value="${personne.nom}" required>
            </div>
            <div class="mb-3">
                <label for="prenom" class="form-label">Prénom</label>
                <input type="text" class="form-control" id="prenom" name="prenom" value="${personne.prenom}" required>
            </div>
            <div class="mb-3">
                <label for="dateNaissance" class="form-label">Date de naissance</label>
                <input type="date" class="form-control" id="dateNaissance" name="dateNaissance" value="${personne.dateNaissance}" required>
            </div>
            <div class="mb-3">
                <label for="type" class="form-label">Type</label>
                <select class="form-control" id="type" name="type" required>
                    <option value="adherent" ${personne.type == 'adherent' ? 'selected' : ''}>Adhérent</option>
                    <option value="bibliothecaire" ${personne.type == 'bibliothecaire' ? 'selected' : ''}>Bibliothécaire</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="login" class="form-label">Login</label>
                <input type="text" class="form-control" id="login" name="login" value="${personne.login}" required>
            </div>
            <div class="mb-3">
                <label for="motDePasse" class="form-label">Mot de passe</label>
                <input type="password" class="form-control" id="motDePasse" name="motDePasse" value="${personne.motDePasse}" required>
            </div>
            <button type="submit" class="btn btn-primary">Enregistrer</button>
            <a href="/biblio/personnes" class="btn btn-secondary">Annuler</a>
        </form>
    </div>
</body>
</html>
