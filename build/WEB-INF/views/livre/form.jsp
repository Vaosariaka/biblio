<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${livre.livreId == 0 ? 'Ajouter' : 'Modifier'} un Livre</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">${livre.livreId == 0 ? 'Ajouter un Livre' : 'Modifier un Livre'}</h1>
        <form action="${livre.livreId == 0 ? '/biblio/livres/save' : '/biblio/livres/update/' + livre.livreId}" method="post">
            <div class="mb-3">
                <label for="titre" class="form-label">Titre</label>
                <input type="text" class="form-control" id="titre" name="titre" value="${livre.titre}" required>
            </div>
            <div class="mb-3">
                <label for="idGenre" class="form-label">Genre</label>
                <select class="form-control" id="idGenre" name="idGenre" required>
                    <c:forEach var="genre" items="${genres}">
                        <option value="${genre.genreId}" ${livre.idGenre == genre.genreId ? 'selected' : ''}>${genre.nom}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="idAuteur" class="form-label">Auteur</label>
                <select class="form-control" id="idAuteur" name="idAuteur" required>
                    <c:forEach var="auteur" items="${auteurs}">
                        <option value="${auteur.auteurId}" ${livre.idAuteur == auteur.auteurId ? 'selected' : ''}>${auteur.nom}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="categorie" class="form-label">Catégorie</label>
                <input type="text" class="form-control" id="categorie" name="categorie" value="${livre.categorie}" required>
            </div>
            <div class="mb-3">
                <label for="nombreExemplaires" class="form-label">Nombre d'exemplaires</label>
                <input type="number" class="form-control" id="nombreExemplaires" name="nombreExemplaires" value="${livre.nombreExemplaires}" required>
            </div>
            <button type="submit" class="btn btn-primary">Enregistrer</button>
            <a href="/biblio/livres" class="btn btn-secondary">Annuler</a>
        </form>
        <c:if test="${not empty message}">
            <div class="alert alert-info mt-3">${message}</div>
        </c:if>
    </div>
</body>
</html>
