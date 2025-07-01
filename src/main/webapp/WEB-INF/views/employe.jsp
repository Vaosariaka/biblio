<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des employés</title>
</head>
<body>
    <h1>Gestion des employés</h1>

    <!-- Messages -->
    <c:if test="${not empty erreur}">
        <div style="color: red;">${erreur}</div>
    </c:if>
    <c:if test="${not empty succes}">
        <div style="color: green;">${succes}</div>
    </c:if>

    <!-- Formulaire d'insertion / modification -->
    <form action="${pageContext.request.contextPath}/employe/save" method="post">
        <input type="hidden" name="id" value="${employe.id}" />

        <label for="id_poste">Poste :</label>
        <select name="id_poste" required>
            <c:if test="${not empty employe.poste}">
                <option value="${employe.poste.id}" selected>${employe.poste.nom}</option>
            </c:if>
            <c:forEach var="p" items="${postes}">
                <c:if test="${employe.poste == null || p.id != employe.poste.id}">
                    <option value="${p.id}">${p.nom}</option>
                </c:if>
            </c:forEach>
        </select>


        <label>Numéro d'identification :</label>
        <input type="text" name="num_identification" value="${employe.numIdentification}" required /><br>

        <label>Nom :</label>
        <input type="text" name="nom" value="${employe.nom}" required /><br>

        <label>Prénom :</label>
        <input type="text" name="prenom" value="${employe.prenom}" required /><br>

        <label>Date de naissance :</label>
        <input type="date" name="date_naissance" value="${employe.dateNaissance}" /><br>

        <label>Contact :</label>
        <input type="text" name="contact" value="${employe.contact}" /><br>

        <label>Date d'embauche :</label>
        <input type="date" name="date_embauche" value="${employe.dateEmbauche}" required /><br>

        <input type="submit" value="Enregistrer" />
    </form>

    <!-- Liste des employés -->
    <h2>Liste des employés</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Poste</th>
                <th>Salaire</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="e" items="${employes}">
            <tr>
                <td>${e.id}</td>
                <td>${e.nom}</td>
                <td>${e.prenom}</td>
                <td>
                    <c:forEach var="p" items="${postes}">
                        <c:if test="${p.id == e.getPoste().id}">${p.nom}</c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="p" items="${postes}">
                        <c:if test="${p.id == e.getPoste().id}">${p.salaire} Ar</c:if>
                    </c:forEach>
                </td>
                <td>
                    <a href="edit/?id=${e.id}"><button>Modifier</button></a>
                    <a href="delete?id=${e.id}" onclick="return confirm('Confirmer la suppression ?')"><button>Supprimer</button></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
