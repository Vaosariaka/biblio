<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Quotas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">Liste des Quotas</h1>
        <a href="/biblio/quotas/new" class="btn btn-primary mb-3">Ajouter un quota</a>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID Adhérent</th>
                    <th>Nom Adhérent</th>
                    <th>Quota Prêts Restants</th>
                    <th>Quota Prolongations Restants</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="quota" items="${quotas}">
                    <tr>
                        <td>${quota.idAdherent}</td>
                        <td>${quota.adherent.personne.nom} ${quota.adherent.personne.prenom}</td>
                        <td>${quota.quotaPretsRestants}</td>
                        <td>${quota.quotaProlongationsRestants}</td>
                        <td>
                            <a href="/biblio/quotas/edit/${quota.idAdherent}" class="btn btn-sm btn-warning">Modifier</a>
                            <a href="/biblio/quotas/delete/${quota.idAdherent}" class="btn btn-sm btn-danger" onclick="return confirm('Confirmer la suppression ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
