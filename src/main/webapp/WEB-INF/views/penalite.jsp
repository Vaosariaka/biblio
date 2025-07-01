<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion de pénalité</title>
</head>
<body>
    <h1>Gestion de pénalité</h1>

    <!-- Affichage du message de retour -->
    <c:if test="${not empty erreur}">
        <div style="color: red;"><strong>${erreur}</strong></div>
    </c:if>
    <c:if test="${not empty succes}">
        <div style="color: green;"><strong>${succes}</strong></div>
    </c:if>

    <form action="save" method="post">
        <input type="hidden" name="id" value="${penalite.id}" />
        <div style="display: flex; gap: 20px;">
            <div>
                <label for="nb_jour">Nombre de jours</label>
                <input type="number" id="nb_jour" name="nb_jour" value="${penalite.nbJour}" required>
            </div>
            <div>
                <label for="pourcent">Pourcentage de réduction</label>
                <input type="number" id="pourcent" name="pourcent" value="${penalite.pourcentage}" required>
            </div>
        </div>
        <div><input type="submit" value="OK"></div>
    </form>

    <c:if test="${not empty penalites}">
        <table border="1" cellpadding="5" cellspacing="0">
            <thead>
                <tr>
                    <th>Nombre de jours d'absence</th>
                    <th>Pourcentage de réduction</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${penalites}">
                    <tr>
                        <td>${p.nbJour}</td>
                        <td>${p.pourcentage}</td>
                        <td>
                            <a href="edit?id=${p.id}"><button type="button">Modifier</button></a>
                            <a href="delete?id=${p.id}"><button type="button">Supprimer</button></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
