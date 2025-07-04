```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/_navbar.jsp"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<div class="container">
    <h1 class="my-4">Liste des Personnes</h1>
    <a href="/biblio/personnes/new" class="btn btn-primary mb-3">Ajouter une personne</a>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Date de Naissance</th>
                <th>Type</th>
                <th>Login</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="personne" items="${personnes}">
                <tr>
                    <td>${personne.personneId}</td>
                    <td>${personne.nom}</td>
                    <td>${personne.prenom}</td>
                    <td>${personne.dateNaissance}</td>
                    <td>${personne.type}</td>
                    <td>${personne.login}</td>
                    <td>
                        <a href="/biblio/personnes/edit/${personne.personneId}" class="btn btn-sm btn-warning">Modifier</a>
                        <a href="/biblio/personnes/delete/${personne.personneId}" class="btn btn-sm btn-danger " onclick="return confirm('Confirmer la suppression ?')">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
```