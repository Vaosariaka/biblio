<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.entity.Stock" %>
<%@ page import="org.example.entity.Composant" %>
<%
    List<Stock> stocks = (List<Stock>) request.getAttribute("stock");
%>
<html>
<head>
    <title>Liste des stocks</title>
    
    <script>
        function confirmAction(msg, url) {
            if(confirm(msg)) {
                window.location.href = url;
            }
        }
    </script>
</head>
<body>
    <h2>Liste des stocks</h2>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Composant</th>
            <th>Date de creation</th>
            <th>Quantité</th>
            <th>Nombre de jour conservation</th>
            <th>Actions</th>
            </tr>
        <% for(Stock s : stocks) { %>
        <tr>
            <td><%= s.getId() %></td>
            <td>
            <c:forEach var="c" items="${composants}">
                        <c:if test="${c.id == s.getComposant().id}">${c.nom}</c:if>
            </c:forEach>
            </td>
            <td><%= s.getdateCreation() %></td>
            <td><%= s.getQtteStock() %></td>
            <td><%= s.getNombre_jour_conservation() %></td>
            <td>
                <a href="#" onclick="confirmAction('Confirmer la suppression ?', '${pageContext.request.contextPath}/stock/delete?id=<%=s.getId()%>')" class="action-link delete-link">Supprimer</a>
                <a href="#" onclick="confirmAction('Confirmer la modification ?', '${pageContext.request.contextPath}/stock/update?id=<%=s.getId()%>')" class="action-link">Modifier</a>
            </td>
        </tr>
        <% } %>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/stock/create" class="action-link">Ajouter un stock</a><br>
</body>
</html>
