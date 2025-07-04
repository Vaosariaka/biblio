
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/_navbar.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<div class="container">
    <h1 class="my-4">${penalite.penaliteId == 0 ? 'Ajouter' : 'Modifier'} une Pénalité</h1>
    <form action="${penalite.penaliteId == 0 ? '/biblio/penalites/save' : '/biblio/penalites/update/' + penalite.penaliteId}" method="post">
        <div class="mb-3">
            <label for="idAdherent" class="form-label">Adhérent</label>
            <select class="form-control" id="idAdherent" name="idAdherent" required>
                <c:forEach var="adherent" items="${adherents}">
                    <option value="${adherent.personneId}" ${penalite.idAdherent == adherent.personneId ? 'selected' : ''}>${adherent.personne.nom} ${adherent.personne.prenom}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="dateDebut" class="form-label">Date Début</label>
            <input type="date" class="form-control" id="dateDebut" name="dateDebut" value="${penalite.dateDebut}" required>
        </div>
        <div class="mb-3">
            <label for="nbreJoursSanction" class="form-label">Nombre de Jours de Sanction</label>
            <input type="number" class="form-control" id="nbreJoursSanction" name="nbreJoursSanction" value="${penalite.nbreJoursSanction}" required>
        </div>
        <button type="submit" class="btn btn-primary">Enregistrer</button>
        <a href="/biblio/penalites" class="btn btn-secondary">Annuler</a>
    </form>
    <c:if test="${not empty message}">
        <div class="alert alert-info mt-3">${message}</div>
    </c:if>
</div>
