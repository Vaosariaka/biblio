<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/_navbar.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<div class="container">
    <h1 class="my-4">Liste des Pénalités</h1>
    <a href="/biblio/penalites/new" class="btn btn-primary mb-3">Ajouter une pénalité</a>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Adhérent</th>
                <th>Date Début</th>
                <th>Nombre de Jours</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="penalite" items="${penalites}">
                <tr>
                    <td>${penalite.penaliteId}</td>
                    <td>${penalite.adherent.personne.nom} ${penalite.adherent.personne.prenom}</td>
                    <td>${penalite.dateDebut}</td>
                    <td>${penalite.nbreJoursSanction}</td>
                    <td>
                        <a href="/biblio/penalites/edit/${penalite.penaliteId}" class="btn btn-sm btn-warning">Modifier</a>
                        <a href="/biblio/penalites/delete/${penalite.penaliteId}" class="btn btn-sm btn-danger" onclick="return confirm('Confirmer la suppression ?')">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
