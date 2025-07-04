<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Adhérents</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">Liste des Adhérents</h1>
        <a href="/biblio/adherents/new" class="btn btn-primary mb-3">Ajouter un adhérent</a>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Catégorie</th>
                    <th>Date début</th>
                    <th>Date fin</th>
                    <th>Quota Prêts</th>
                    <th>Quota Prolongations</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="adherent" items="${adherents}">
                    <tr>
                        <td>${adherent.personneId}</td>
                        <td>${adherent.personne.nom}</td>
                        <td>${adherent.categorie}</td>
                        <td>${adherent.dateDebut}</td>
                        <td>${adherent.dateFin}</td>
                        <td>${adherent.quotaPrets}</td>
                        <td>${adherent.quotaProlongations}</td>
                        <td>
                            <a href="/biblio/adherents/edit/${adherent.personneId}" class="btn btn-sm btn-warning">Modifier</a>
                            <a href="/biblio/adherents/delete/${adherent.personneId}" class="btn btn-sm btn-danger" onclick="return confirm('Confirmer la suppression ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
