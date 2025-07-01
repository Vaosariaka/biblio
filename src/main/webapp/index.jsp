<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/_navbar.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<style>
    .home-container { width:90%; margin:auto; text-align:center; margin-top:60px; }
    .home-container h1 { font-size:2em; color:#222; }
    .home-container a { padding:12px 30px; background:#222; color:#fff; text-decoration:none; border-radius:4px; margin-right:20px; transition:background 0.2s; }
    .home-container a:last-child { background:#555; margin-right:0; }
    .home-container a:hover { background:#444; }
</style>
<div class="home-container">
    <h1>Bienvenue dans l'application</h1>
    <p>
        <!-- <p><a href="${pageContext.request.contextPath}/mvtcontrat/create">Voir contrats</a></p>
       <p><a href="${pageContext.request.contextPath}/entreprise/create">Creer entreprise</a></p>  -->
           <p><a href="${pageContext.request.contextPath}/categories">Voir les categories</a></p> 
           <p><a href="${pageContext.request.contextPath}/employe/paiement_salaire">Le payement salaire</a></p> 
           <p><a href="${pageContext.request.contextPath}/penalite/">La Pénalité </a></p> 
         <p> <a href="${pageContext.request.contextPath}/depense/">Les Dépenses </a></p>  
            <p><a href="${pageContext.request.contextPath}/poste/">Les Postes </a></p>
            <p><a href="${pageContext.request.contextPath}/employe/">Les employes </a></p>
        </p>
</div>
