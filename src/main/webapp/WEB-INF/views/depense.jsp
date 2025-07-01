<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Gestion des dépenses</title>
</head>
<body>
    <h2>Formulaire Dépense</h2>

    <c:if test="${not empty succes}">
        <div style="color:green">${succes}</div>
    </c:if>

    <form action="save" method="post">
        <input type="hidden" name="id" value="${depense.id}">
        <div>
            <label>Description :</label>
            <input type="text" name="description" value="${depense.description}" required>
        </div>
        <div>
            <label>Montant :</label>
            <input type="number" name="somme" step="0.01" value="${depense.somme}" required>
        </div>
        <div>
            <input type="submit" value="Enregistrer">
        </div>
    </form>

    <c:if test="${not empty depenses}">
        <div>
            <h3>Liste des Dépenses</h3>
            <table border="1">
                <tr>
                    <th>Description</th>
                    <th>Date</th>
                    <th>Montant</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="d" items="${depenses}">
                    <tr>
                        <td>${d.description}</td>
                        <td>${d.dateCreation}</td>
                        <td>${d.somme}</td>
                        <td>
                            <a href="edit?id=${d.id}"><button>Modifier</button></a> |
                            <a href="delete?id=${d.id}"><button>Supprimer</button></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
    
</body>
</html>
