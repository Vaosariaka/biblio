<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/_navbar.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<style>
    .home-container { width: 90%; margin: auto; text-align: center; margin-top: 60px; }
    .home-container h1 { font-size: 2em; color: #222; }
    .home-container a { padding: 12px 30px; background: #222; color: #fff; text-decoration: none; border-radius: 4px; margin-right: 20px; transition: background 0.2s; }
    .home-container a:last-child { background: #555; margin-right: 0; }
    .home-container a:hover { background: #444; }
</style>
<div class="home-container">
    <h1>Bienvenue dans l'application de gestion de bibliothèque</h1>
    <p>
        <p><a href="${pageContext.request.contextPath}/Genres">genres</a></p>
        <p><a href="${pageContext.request.contextPath}/personnes">Voir les personnes</a></p>
        <p><a href="${pageContext.request.contextPath}/livres">Voir les livres</a></p>
        <p><a href="${pageContext.request.contextPath}/adherents">Voir les adhérents</a></p>
        <p><a href="${pageContext.request.contextPath}/quotas">Voir les quotas</a></p>
        <p><a href="${pageContext.request.contextPath}/jours_feries">Voir les jours fériés</a></p>
        <p><a href="${pageContext.request.contextPath}/prets">Voir les prêts</a></p>
        <p><a href="${pageContext.request.contextPath}/reservations">Voir les réservations</a></p>
        <p><a href="${pageContext.request.contextPath}/penalites">Voir les pénalités</a></p>
    </p>
</div>
