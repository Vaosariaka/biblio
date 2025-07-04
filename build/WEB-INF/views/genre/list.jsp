<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Genres</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">Liste des Genres</h1>
        <a href="/biblio/Genres/new" class="btn btn-primary mb-3">Ajouter un Genre</a>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="Genre" items="${Genres}">
                    <tr>
                        <td>${Genre.GenreId}</td>
                        <td>${Genre.nom}</td>
                        <td>
                            <a href="/biblio/Genres/edit/${Genre.GenreId}" class="btn btn-sm btn-warning">Modifier</a>
                            <a href="/biblio/Genres/delete/${Genre.GenreId}" class="btn btn-sm btn-danger" onclick="return confirm('Confirmer la suppression ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
