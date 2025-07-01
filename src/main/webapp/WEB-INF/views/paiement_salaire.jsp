<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Payement salaire</title>
</head>
<body>
    <h1>Payement Salaire</h1>

    <!-- Affichage du message de retour -->
    <c:if test="${not empty erreur}">
        <div style="color: red;">
            <strong>${erreur}</strong>
        </div>
    </c:if>
    <c:if test="${not empty succes}">
        <div style="color: green;">
            <strong>${succes}</strong>
        </div>
    </c:if>

    <form action="t_paiement_salaire" method="post">
        <div>
            <label for="id_employe">Employé</label>
            <select name="id_employe" id="id_employe" required>
                <option value="">-- Choisir un employé --</option>
                <c:forEach var="e" items="${employes}">
                    <option value="${e.id}">${e.nom} ${e.prenom} (${e.getNumIdentification()})</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label for="montant">Montant à payer</label>
            <input type="number" name="montant" id="montant" placeholder="Saisir le montant à payer" required>
        </div>
        <div>
            <input type="submit" value="OK">
        </div>
    </form>
</body>
</html>