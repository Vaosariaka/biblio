<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un Prêt</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">Ajouter un Prêt</h1>
        <form action="/biblio/prets/save" method="post">
            <div class="mb-3">
                <label for="idAdherent" class="form-label">Adhérent</label>
                <select class="form-control" id="idAdherent" name="idAdherent" required>
                    <c:forEach var="adherent" items="${adherents}">
                        <option value="${adherent.personneId}">${adherent.personne.nom} ${adherent.personne.prenom}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="idExemplaire" class="form-label">Exemplaire</label>
                <select class="form-control" id="idExemplaire" name="idExemplaire" required>
                    <c:forEach var="exemplaire" items="${exemplaires}">
                        <option value="${exemplaire.exemplaireId}">${exemplaire.livre.titre} (ID: ${exemplaire.exemplaireId})</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="datePret" class="form-label">Date Prêt</label>
                <input type="date" class="form-control" id="datePret" name="datePret" required>
            </div>
            <div class="mb-3">
                <label for="dureePret" class="form-label">Durée Prêt (jours)</label>
                <input type="number" class="form-control" id="dureePret" name="dureePret" required>
            </div>
            <div class="mb-3">
                <label for="regleAjustement" class="form-label">Règle d'ajustement</label>
                <select class="form-control" id="regleAjustement" name="regleAjustement" required>
                    <option value="NEXT">Prochain jour ouvrable</option>
                    <option value="PREVIOUS">Jour ouvrable précédent</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Enregistrer</button>
            <a href="/biblio/prets" class="btn btn-secondary">Annuler</a>
        </form>
        <c:if test="${not empty message}">
            <div class="alert alert-info mt-3">${message}</div>
        </c:if>
    </div>
</body>
</html>
