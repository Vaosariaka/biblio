<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Prolonger un Prêt</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">Prolonger le Prêt ID ${pret.pretId}</h1>
        <form action="/biblio/prets/prolonger/${pret.pretId}" method="post">
            <div class="mb-3">
                <label for="nouvelleDateRetour" class="form-label">Nouvelle Date de Retour</label>
                <input type="date" class="form-control" id="nouvelleDateRetour" name="nouvelleDateRetour" required>
            </div>
            <div class="mb-3">
                <label for="regleAjustement" class="form-label">Règle d'ajustement</label>
                <select class="form-control" id="regleAjustement" name="regleAjustement" required>
                    <option value="NEXT">Prochain jour ouvrable</option>
                    <option value="PREVIOUS">Jour ouvrable précédent</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Prolonger</button>
            <a href="/biblio/prets" class="btn btn-secondary">Annuler</a>
        </form>
        <c:if test="${not empty message}">
            <div class="alert alert-info mt-3">${message}</div>
        </c:if>
    </div>
</body>
</html>
