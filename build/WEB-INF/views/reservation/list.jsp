<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Réservations</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">Liste des Réservations</h1>
        <a href="/biblio/reservations/new" class="btn btn-primary mb-3">Ajouter une réservation</a>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Adhérent</th>
                    <th>Exemplaire</th>
                    <th>Date Réservation</th>
                    <th>Date Prêt</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reservation" items="${reservations}">
                    <tr>
                        <td>${reservation.reservationId}</td>
                        <td>${reservation.adherent.personne.nom}</td>
                        <td>${reservation.exemplaire.livre.titre}</td>
                        <td>${reservation.dateReservation}</td>
                        <td>${reservation.datePret}</td>
                        <td>
                            <a href="/biblio/reservations/edit/${reservation.reservationId}" class="btn btn-sm btn-warning">Modifier</a>
                            <a href="/biblio/reservations/delete/${reservation.reservationId}" class="btn btn-sm btn-danger" onclick="return confirm('Confirmer la suppression ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
