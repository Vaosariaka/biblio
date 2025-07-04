<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Prêts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">Liste des Prêts</h1>
        <a href="/biblio/prets/new" class="btn btn-primary mb-3">Ajouter un prêt</a>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Adhérent</th>
                    <th>Exemplaire</th>
                    <th>Date Prêt</th>
                    <th>Date Retour</th>
                    <th>Prolongations</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="pret" items="${prets}">
                    <tr>
                        <td>${pret.pretId}</td>
                        <td>${pret.adherent.personne.nom}</td>
                        <td>${pret.exemplaire.livre.titre}</td>
                        <td>${pret.datePret}</td>
                        <td>${pret.dateRetour}</td>
                        <td>${pret.nbProlongations}</td>
                        <td>
                            <a href="/biblio/prets/prolonger/${pret.pretId}" class="btn btn-sm btn-info">Prolonger</a>
                            <a href="/biblio/prets/delete/${pret.pretId}" class="btn btn-sm btn-danger" onclick="return confirm('Confirmer la suppression ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
