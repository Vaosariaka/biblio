<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav style="background:#222; color:#fff; padding:10px 0; margin-bottom:20px;">
    <div style="width:90%; margin:auto; display:flex; align-items:center;">
        <a href="${pageContext.request.contextPath}/" style="color:#fff; text-decoration:none; font-weight:bold; font-size:1.2em; margin-right:30px;">Accueil</a>
        <a href="${pageContext.request.contextPath}/films" style="color:#fff; text-decoration:none; margin-right:20px;">Films</a>
        <a href="${pageContext.request.contextPath}/categories" style="color:#fff; text-decoration:none;">Categories</a>
        <a href="${pageContext.request.contextPath}/login/disconnect" style="color:#fff; text-decoration:none;">Deconnexion</a>
    </div>
</nav>
