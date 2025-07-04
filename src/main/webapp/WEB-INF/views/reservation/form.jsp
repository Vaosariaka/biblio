<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${reservation.reservationId == 0 ? 'Ajouter' : 'Modifier'} une Réservation</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">${reservation.reservationId == 0 ? 'Ajouter une Réservation' : 'Modifier une Réservation'}</h1>
        <form action="${reservation.reservationId == 0 ? '/biblio/reservations/save' : '/biblio/reservations/update/' + reservation.reservationId}" method="post">
            <div class="mb-3">
                <label for="idAdherent" class="form-label">Adhérent</label>
                <select class="form-control" id="idAdherent" name="idAdherent" required>
                    <c:forEach var="adherent" items="${adherents}">
                        <option value="${adherent.personneId}" ${reservation.idAdherent == adherent.personneId ? 'selected' : ''}>${adherent.personne.nom} ${adherent.personne.prenom}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="idExemplaire" class="form-label">Exemplaire</label>
                <select class="form-control" id="idExemplaire" name="idExemplaire" required>
                    <c:forEach var="exemplaire" items="${exemplaires}">
                        <option value="${exemplaire.exemplaireId}" ${reservation.idExemplaire == exemplaire.exemplaireId ? 'selected' : ''}>${exemplaire.livre.titre} (ID: ${exemplaire.exemplaireId})</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="dateReservation" class="form-label">Date Réservation</label>
                <input type="date" class="form-control" id="dateReservation" name="dateReservation" value="${reservation.dateReservation}" required>
            </div>
            <div class="mb-3">
                <label for="datePret" class="form-label">Date Prêt</label>
                <input type="date" class="form-control" id="datePret" name="datePret" value="${reservation.datePret}">
            </div>
            <div class="mb-3">
                <label for="regleAjustement" class="form-label">Règle d'ajustement</label>
                <select class="form-control" id="regleAjustement" name="regleAjustement" required>
                    <option value="NEXT" ${regleAjustement == 'NEXT' ? 'selected' : ''}>Prochain jour ouvrable</option>
                    <option value="PREVIOUS" ${regleAjustement == 'PREVIOUS' ? 'selected' : ''}>Jour ouvrable précédent</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Enregistrer</button>
            <a href="/biblio/reservations" class="btn btn-secondary">Annuler</a>
        </form>
        <c:if test="${not empty message}">
            <div class="alert alert-info mt-3">${message}</div>
        </c:if>
    </div>
</body>
</html>
