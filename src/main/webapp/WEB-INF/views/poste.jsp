<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gestion des Postes</title>
</head>
<body>
    <h2>Formulaire Poste</h2>

    <c:if test="${not empty succes}">
        <div style="color:green">${succes}</div>
    </c:if>

    <form action="save" method="post">
        <input type="hidden" name="id" value="${poste.id}">
        <div>
            <label>Nom du poste :</label>
            <input type="text" name="nom" value="${poste.nom}" required>
        </div>
        <div>
            <label>Salaire :</label>
            <input type="number" name="salaire" step="0.01" value="${poste.salaire}" required>
        </div>
        <div>
            <input type="submit" value="Enregistrer">
        </div>
    </form>

    <c:if test="${not empty postes}">
        <div >
            <h3>Liste des Postes</h3>
            <table border="1">
                <tr>
                    <th>Nom</th>
                    <th>Salaire</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="p" items="${postes}">
                    <tr>
                        <td>${p.nom}</td>
                        <td>${p.salaire}</td>
                        <td>
                            <a href="edit?id=${p.id}">Modifier</a> |
                            <a href="delete?id=${p.id}">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
    
</body>
</html>
