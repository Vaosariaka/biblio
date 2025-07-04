<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Livres</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">Liste des Livres</h1>
        <a href="/biblio/livres/new" class="btn btn-primary mb-3">Ajouter un livre</a>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Titre</th>
                    <th>Genre</th>
                    <th>Auteur</th>
                    <th>Catégorie</th>
                    <th>Nombre Exemplaires</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="livre" items="${livres}">
                    <tr>
                        <td>${livre.livreId}</td>
                        <td>${livre.titre}</td>
                        <td>${livre.genre.nom}</td>
                        <td>${livre.auteur.nom}</td>
                        <td>${livre.categorie}</td>
                        <td>${livre.nombreExemplaires}</td>
                        <td>
                            <a href="/biblio/livres/edit/${livre.livreId}" class="btn btn-sm btn-warning">Modifier</a>
                            <a href="/biblio/livres/delete/${livre.livreId}" class="btn btn-sm btn-danger" onclick="return confirm('Confirmer la suppression ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
