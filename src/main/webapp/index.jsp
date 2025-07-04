<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/_navbar.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<style>
    .login-container {
        width: 90%;
        max-width: 400px;
        margin: 100px auto;
        text-align: center;
        background: #fff;
        padding: 20px;
        border-radius: 4px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .login-container h1 {
        font-size: 1.8em;
        color: #222;
        margin-bottom: 20px;
    }
    .login-container input {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    .login-container button {
        padding: 12px 30px;
        background: #222;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background 0.2s;
    }
    .login-container button:hover {
        background: #444;
    }
    .error {
        color: red;
        margin-top: 10px;
    }
</style>
<div class="login-container">
    <h1>Connexion</h1>
    <form action="/biblio/auth/login" method="post">
        <input type="text" name="login" placeholder="Login" required>
        <input type="password" name="mot_de_passe" placeholder="Mot de passe" required>
        <button type="submit">Se connecter</button>
    </form>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
</div>