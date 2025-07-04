```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${Genre.GenreId == 0 ? 'Ajouter' : 'Modifier'} un Genre</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">${Genre.GenreId == 0 ? 'Ajouter un Genre' : 'Modifier un Genre'}</h1>
        <form action="${Genre.GenreId == 0 ? '/biblio/Genres/save' : '/biblio/Genres/update/' + Genre.GenreId}" method="post">
            <div class="mb-3">
                <label for="nom" class="form-label">Nom</label>
                <input type="text" class="form-control" id="nom" name="nom" value="${Genre.nom}" required>
            </div>
            <button type="submit" class="btn btn-primary">Enregistrer</button>
            <a href="/biblio/Genres" class="btn btn-secondary">Annuler</a>
        </form>
        <c:if test="${not empty message}">
            <div class="alert alert-info mt-3">${message}</div>
        </c:if>
    </div>
</body>
</html>
```