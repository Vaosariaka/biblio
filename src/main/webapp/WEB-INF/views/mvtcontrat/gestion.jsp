<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Mouvements de Contrat</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
            min-height: 100vh; 
            padding: 20px; 
            background: transparent; 
        }
        .container { 
            max-width: 1200px; 
            margin: 0 auto; 
            background: transparent; 
            border-radius: 20px; 
            padding: 30px; 
            box-shadow: 0 20px 40px #007e5d; 
            backdrop-filter: blur(10px); 
        }
        .header { 
            text-align: center; 
            margin-bottom: 40px; 
            color: #f8c828; 
        }
        .header h1 { 
            font-size: 2.5rem; 
            margin-bottom: 10px; 
        }
        .header p { 
            font-size: 1.1rem; 
            color: #f8c828; 
        }
        .tabs { 
            display: flex; 
            margin-bottom: 30px; 
            background: transparent; 
            border-radius: 15px; 
            padding: 5px; 
        }
        .tab-button { 
            flex: 1; 
            padding: 15px 20px; 
            border: none; 
            background: transparent; 
            cursor: pointer; 
            border-radius: 10px; 
            font-size: 1rem; 
            font-weight: 600; 
            transition: all 0.3s ease; 
            color: #f8c828; 
            border: 1px solid #007e5d; 
        }
        .tab-button.active { 
            background: transparent; 
            color: #f8c828; 
            transform: translateY(-2px); 
            box-shadow: 0 5px 15px #007e5d; 
            border: 1px solid #007e5d; 
        }
        .tab-content { 
            display: none; 
        }
        .tab-content.active { 
            display: block; 
            animation: fadeIn 0.5s ease; 
        }
        @keyframes fadeIn { 
            from { opacity: 0; transform: translateY(20px); } 
            to { opacity: 1; transform: translateY(0); } 
        }
        .form-group { 
            margin-bottom: 25px; 
        }
        .form-group label { 
            display: block; 
            margin-bottom: 8px; 
            font-weight: 600; 
            color: #f8c828; 
        }
        .form-control { 
            width: 100%; 
            padding: 15px; 
            border: 2px solid #f8c828; 
            border-radius: 10px; 
            font-size: 1rem; 
            transition: all 0.3s ease; 
            background: transparent; 
            color: rgb(24, 6, 6);
        }
        .form-control:focus { 
            outline: none; 
            border-color: #007e5d; 
            box-shadow: 0 0 0 3px #f8c828; 
            transform: translateY(-2px); 
        }
        .btn { 
            padding: 15px 30px; 
            border: none; 
            border-radius: 10px; 
            cursor: pointer; 
            font-size: 1rem; 
            font-weight: 600; 
            transition: all 0.3s ease; 
            text-transform: uppercase; 
            letter-spacing: 0.5px; 
            border: 1px solid; 
        }
        .btn-primary { 
            background: transparent; 
            color: #f8c828; 
            border-color: #f8c828; 
        }
        .btn-primary:hover { 
            transform: translateY(-3px); 
            box-shadow: 0 10px 25px #007e5d; 
        }
        .btn-secondary { 
            background: transparent; 
            color: #f8c828; 
            border-color: #f8c828; 
        }
        .btn-danger { 
            background: transparent; 
            color: #ff4d4f; 
            border-color: #ff4d4f; 
        }
        .btn-sm { 
            padding: 8px 16px; 
            font-size: 0.9rem; 
        }
        .success-message, .error-message { 
            padding: 15px; 
            border-radius: 10px; 
            margin-bottom: 20px; 
            text-align: center; 
            font-weight: 600; 
            background: transparent; 
        }
        .success-message { 
            color: #007e5d; 
            border: 1px solid #007e5d; 
        }
        .error-message { 
            color: #ff4d4f; 
            border: 1px solid #ff4d4f; 
        }
        .mvt-contrat-table { 
            width: 100%; 
            border-collapse: collapse; 
            background: transparent; 
            border-radius: 10px; 
            overflow: hidden; 
            margin-top: 20px;
        }
        .mvt-contrat-table th, .mvt-contrat-table td { 
            padding: 15px; 
            text-align: left; 
            border-bottom: 1px solid #f8c828; 
            color: rgb(7, 6, 6);
        }
        .mvt-contrat-table th { 
            background: #007e5d; 
            font-weight: 600; 
            color: #f8c828; 
        }
        .mvt-contrat-table tr:hover { 
            background: rgba(0, 126, 93, 0.2); 
        }
        select option {
            background: #333;
            color: white;
        }
        @media (max-width: 768px) {
            .container { padding: 20px; }
            .header h1 { font-size: 2rem; }
            .tabs { flex-direction: column; }
            .mvt-contrat-table { display: block; overflow-x: auto; }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Gestion des Mouvements de Contrat</h1>
            <p>Antananarivo - Madagascar</p>
            <c:if test="${not empty entreprise}">
                <p>Entreprise : ${entreprise.nom}</p>
            </c:if>
        </div>
        
        <c:if test="${not empty message}">
            <div class="${message.contains('Erreur') ? 'error-message' : 'success-message'}">${message}</div>
        </c:if>

        <div class="tabs">
            <button class="tab-button <c:if test='${param.tab != "list"}'>active</c:if>" onclick="showTab('create')">Créer Mouvement</button>
            <button class="tab-button <c:if test='${param.tab == "list"}'>active</c:if>" onclick="showTab('list')">Liste des Mouvements</button>
            <a href="${pageContext.request.contextPath}/entreprise/list?tab=list" class="btn btn-secondary" style="margin-left: 10px;">Retour aux Entreprises</a>
        </div>

        <!-- Onglet Création -->
        <div id="create-tab" class="tab-content <c:if test='${param.tab != "list"}'>active</c:if>">
            <form action="${pageContext.request.contextPath}/mvtcontrat/save" method="post">
                <input type="hidden" name="id" value="${mvtContrat.id}">
                
                <div class="form-group">
                    <label for="entrepriseNom">Entreprise:</label>
                    <c:choose>
                        <c:when test="${not empty entreprise}">
                            <input type="hidden" name="entrepriseNom" value="${entreprise.nom}">
                            <input type="text" class="form-control" value="${entreprise.nom}" disabled>
                        </c:when>
                        <c:otherwise>
                            <select id="entrepriseNom" name="entrepriseNom" class="form-control" required>
                                <option value="">Sélectionnez une entreprise</option>
                                <c:forEach items="${entreprises}" var="ent">
                                    <option value="${ent.nom}" ${mvtContrat.entreprise != null && mvtContrat.entreprise.nom == ent.nom ? 'selected' : ''}>
                                        ${ent.nom}
                                    </option>
                                </c:forEach>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </div>
                
                <div class="form-group">
                    <label for="typeMvt">Type:</label>
                    <select id="typeMvt" name="typeMvt" class="form-control" required>
                        <option value="">Sélectionnez un type</option>
                        <option value="0" ${mvtContrat.typeMvt == 0 ? 'selected' : ''}>Début</option>
                        <option value="1" ${mvtContrat.typeMvt == 1 ? 'selected' : ''}>Fin</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="dateMvt">Date:</label>
                    <input type="date" id="dateMvt" name="dateMvt" class="form-control"
                           value="<fmt:formatDate value='${mvtContrat.dateMvt}' pattern='yyyy-MM-dd'/>" required>
                </div>
                
                <button type="submit" class="btn btn-primary">
                    ${mvtContrat.id != null ? 'Mettre à jour' : 'Enregistrer'} le mouvement
                </button>
            </form>
        </div>

        <!-- Onglet Liste -->
        <div id="list-tab" class="tab-content <c:if test='${param.tab == "list"}'>active</c:if>">
            <c:choose>
                <c:when test="${empty mvtContrats}">
                    <div style="text-align: center; padding: 50px; color: #f8c828;">
                        <h3>Aucun mouvement de contrat enregistré</h3>
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="mvt-contrat-table">
                        <thead>
                            <tr>
                                <th>Entreprise</th>
                                <th>Type</th>
                                <th>Date</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="mvt" items="${mvtContrats}">
                                <tr>
                                    <td>${mvt.entreprise.nom}</td>
                                    <td>${mvt.typeMvt == 0 ? 'Début' : 'Fin'}</td>
                                    <td><fmt:formatDate value="${mvt.dateMvt}" pattern="dd/MM/yyyy"/></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/mvtcontrat/edit/${mvt.id}<c:if test='${not empty entreprise}'>?entrepriseId=${entreprise.id}</c:if>" 
                                           class="btn btn-primary btn-sm">✏️ Modifier</a>
                                        <a href="${pageContext.request.contextPath}/mvtcontrat/delete/${mvt.id}<c:if test='${not empty entreprise}'>?entrepriseId=${entreprise.id}</c:if>" 
                                           class="btn btn-danger btn-sm" 
                                           onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce mouvement ?')">🗑️ Supprimer</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <script>
        function showTab(tabName) {
            document.querySelectorAll('.tab-button').forEach(btn => btn.classList.remove('active'));
            document.querySelectorAll('.tab-content').forEach(content => content.classList.remove('active'));
            
            document.getElementById(tabName + '-tab').classList.add('active');
            document.querySelector(`.tab-button[onclick="showTab('${tabName}')"]`).classList.add('active');
            
            // Mettre à jour l'URL sans recharger la page
            const url = new URL(window.location.href);
            url.searchParams.set('tab', tabName);
            window.history.pushState({}, '', url);
        }

        // Au chargement de la page, vérifier le paramètre 'tab' dans l'URL
        document.addEventListener('DOMContentLoaded', () => {
            const urlParams = new URLSearchParams(window.location.search);
            const currentTab = urlParams.get('tab') || 'create';
            showTab(currentTab);
        });
    </script>
</body>
</html>