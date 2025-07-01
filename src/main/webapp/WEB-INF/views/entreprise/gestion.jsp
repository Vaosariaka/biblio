<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Entreprises</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; min-height: 100vh; padding: 20px; background: transparent; }
        .container { max-width: 1200px; margin: 0 auto; background: transparent; border-radius: 20px; padding: 30px; box-shadow: 0 20px 40px #007e5d; backdrop-filter: blur(10px); }
        .header { text-align: center; margin-bottom: 40px; color: #f8c828; }
        .header h1 { font-size: 2.5rem; margin-bottom: 10px; }
        .header p { font-size: 1.1rem; color: #f8c828; }
        .tabs { display: flex; margin-bottom: 30px; background: transparent; border-radius: 15px; padding: 5px; }
        .tab-button { flex: 1; padding: 15px 20px; border: none; background: transparent; cursor: pointer; border-radius: 10px; font-size: 1rem; font-weight: 600; transition: all 0.3s ease; color: #f8c828; border: 1px solid #007e5d; }
        .tab-button.active { background: transparent; color: #f8c828; transform: translateY(-2px); box-shadow: 0 5px 15px #007e5d; border: 1px solid #007e5d; }
        .tab-content { display: none; }
        .tab-content.active { display: block; animation: fadeIn 0.5s ease; }
        @keyframes fadeIn { from { opacity: 0; transform: translateY(20px); } to { opacity: 1; transform: translateY(0); } }
        .form-group { margin-bottom: 25px; }
        .form-group label { display: block; margin-bottom: 8px; font-weight: 600; color: #f8c828; }
        .form-control { width: 100%; padding: 15px; border: 2px solid #f8c828; border-radius: 10px; font-size: 1rem; transition: all 0.3s ease; background: transparent; color: #f8c828; }
        .form-control:focus { outline: none; border-color: #007e5d; box-shadow: 0 0 0 3px #f8c828; transform: translateY(-2px); }
        .form-control:invalid { border-color: #f8c828; }
        .btn { padding: 15px 30px; border: none; border-radius: 10px; cursor: pointer; font-size: 1rem; font-weight: 600; transition: all 0.3s ease; text-transform: uppercase; letter-spacing: 0.5px; border: 1px solid; }
        .btn-primary { background: transparent; color: #007e5d; border-color: #f8c828; }
        .btn-primary:hover { transform: translateY(-3px); box-shadow: 0 10px 25px #007e5d; }
        .btn-secondary { background: transparent; color: #f8c828; border-color: #f8c828; }
        .btn-danger { background: transparent; color: #ff4d4f; border-color: #ff4d4f; }
        .btn-sm { padding: 8px 16px; font-size: 0.9rem; }
        .success-message, .error-message { padding: 15px; border-radius: 10px; margin-bottom: 20px; text-align: center; font-weight: 600; background: transparent; }
        .success-message { color: #007e5d; border: 1px solid #007e5d; }
        .error-message { color: #007e5d; border: 1px solid #f8c828; }
        #map { width: 100%; height: 400px; border-radius: 15px; margin: 20px 0; box-shadow: 0 10px 30px #007e5d; }
        .coordinates-display { background: transparent; padding: 15px; border-radius: 10px; margin-top: 10px; font-family: monospace; border: 1px solid #f8c828; }
        .quartier-dropdown { position: relative; display: inline-block; margin-bottom: 15px; }
        .quartier-list { display: none; position: absolute; background: rgba(0, 126, 93, 0.9); border: 1px solid #f8c828; border-radius: 10px; max-height: 200px; overflow-y: auto; width: 200px; z-index: 1000; backdrop-filter: blur(5px); }
        .quartier-list.show { display: block; }
        .quartier-list-item { padding: 10px; cursor: pointer; color: #f8c828; border-bottom: 1px solid #f8c828; }
        .quartier-list-item:hover { background: rgba(248, 200, 40, 0.2); }
        .quartier-list-item.selected { background: rgba(248, 200, 40, 0.3); color: #f8c828; }
        .quartier-list-item:last-child { border-bottom: none; }
        .entreprise-list { display: grid; gap: 20px; }
        .entreprise-card { background: transparent; border-radius: 15px; padding: 25px; box-shadow: 0 10px 30px #007e5d; transition: all 0.3s ease; border: 1px solid #f8c828; }
        .entreprise-card:hover { transform: translateY(-5px); box-shadow: 0 20px 40px #007e5d; }
        .entreprise-name { font-size: 1.3rem; font-weight: bold; color: #f8c828; margin-bottom: 10px; }
        .entreprise-info { display: grid; grid-template-columns: 1fr 1fr; gap: 15px; margin-bottom: 20px; }
        .info-item { display: flex; align-items: center; gap: 10px; color: #f8c828; }
        .info-icon { width: 20px; height: 20px; fill: #007e5d; }
        .entreprise-actions { display: flex; gap: 10px; flex-wrap: wrap; }
        @media (max-width: 768px) {
            .container { padding: 20px; margin: 10px; }
            .header h1 { font-size: 2rem; }
            .tabs { flex-direction: column; }
            .entreprise-info { grid-template-columns: 1fr; }
            .entreprise-actions { justify-content: center; }
            .quartier-dropdown { width: 100%; }
            .quartier-list { width: 100%; }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Gestion des Entreprises</h1>
            <p>Antananarivo - Madagascar</p>
        </div>
        <c:if test="${not empty message}">
            <div class="${message.contains('Erreur') ? 'error-message' : 'success-message'}">${message}</div>
        </c:if>
        <div class="tabs">
            <button class="tab-button <c:if test='${tab != \"list\"}'>active</c:if>" onclick="showTab('create')">Créer Entreprise</button>
            <button class="tab-button <c:if test='${tab == \"list\"}'>active</c:if>" onclick="showTab('list')">Liste des Entreprises</button>
        </div>

        <!-- Onglet Création -->
        <div id="create-tab" class="tab-content <c:if test='${tab != \"list\"}'>active</c:if>">
            <c:choose>
                <c:when test="${entreprise.id != null}">
                    <form id="entreprise-form" action="${pageContext.request.contextPath}/entreprise/update" method="post">
                        <input type="hidden" name="id" value="${entreprise.id}"/>
                </c:when>
                <c:otherwise>
                    <form id="entreprise-form" action="${pageContext.request.contextPath}/entreprise/save" method="post">
                </c:otherwise>
            </c:choose>
                <input type="hidden" id="quartier" name="quartier" value="${entreprise.quartier}"/>
                <div class="form-group">
                    <label for="nom">Nom de l'entreprise *</label>
                    <input type="text" id="nom" name="nom" class="form-control" required value="${entreprise.nom}" placeholder="Entrez le nom de l'entreprise"/>
                </div>
                <div class="form-group">
                    <label>Sélectionnez un quartier d'Antananarivo</label>
                    <div class="quartier-dropdown">
                        <button type="button" class="btn btn-secondary" onclick="toggleQuartierList()">
                            <span id="selected-quartier">${not empty entreprise.quartier ? entreprise.quartier : 'Choisir un quartier'}</span>
                        </button>
                        <div id="quartier-list" class="quartier-list"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="adresse">Adresse exacte *</label>
                    <input type="text" id="adresse" name="adresse" class="form-control" required value="${entreprise.adresse}" placeholder="Entrez l'adresse exacte"/>
                </div>
                <div class="form-group">
                    <label>Localisation sur la carte (Vue Satellite)</label>
                    <div id="map"></div>
                    <div class="coordinates-display">
                        <strong>Coordonnées sélectionnées :</strong><br>
                        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 10px; margin-top: 10px;">
                            <div>
                                <label for="latitude">Latitude:</label>
                                <input type="number" step="0.000001" id="latitude" name="latitude" class="form-control" value="${entreprise.latitude != null ? entreprise.latitude : ''}" placeholder="Latitude"/>
                            </div>
                            <div>
                                <label for="longitude">Longitude:</label>
                                <input type="number" step="0.000001" id="longitude" name="longitude" class="form-control" value="${entreprise.longitude != null ? entreprise.longitude : ''}" placeholder="Longitude"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="debutDateContrat">Date de début du contrat</label>
                    <input type="date" id="debutDateContrat" name="debutDateContrat" class="form-control" value="<fmt:formatDate value='${entreprise.debutDateContrat}' pattern='yyyy-MM-dd'/>"/>
                </div>
                <button type="submit" class="btn btn-primary">${entreprise.id != null ? 'Mettre à jour' : 'Enregistrer'} l'entreprise</button>
            </form>
        </div>

        <div id="list-tab" class="tab-content <c:if test='${tab == \"list\"}'>active</c:if>">
            <div class="entreprise-list">
                <c:choose>
                    <c:when test="${empty entreprises}">
                        <div style="text-align: center; padding: 50px; color: #f8c828;">
                            <h3>Aucune entreprise enregistrée</h3>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="entreprise" items="${entreprises}">
                            <div class="entreprise-card">
                                <div class="entreprise-name">${entreprise.nom}</div>
                                <div class="entreprise-info">
                                    <div class="info-item">
                                        <svg class="info-icon" viewBox="0 0 24 24">
                                            <path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/>
                                        </svg>
                                        <span><strong>Adresse:</strong> <c:out value="${entreprise.adresse != null ? entreprise.adresse : 'Non défini'}"/></span>
                                    </div>
                                    <div class="info-item">
                                        <svg class="info-icon" viewBox="0 0 24 24">
                                            <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/>
                                        </svg>
                                        <span><strong>Coordonnées:</strong> <c:out value="${entreprise.latitude != null ? entreprise.latitude : 'Non défini'}, ${entreprise.longitude != null ? entreprise.longitude : 'Non défini'}"/></span>
                                    </div>
                                    <div class="info-item">
                                        <svg class="info-icon" viewBox="0 0 24 24">
                                            <path d="M9 11H7v6h2v-6zm4 0h-2v6h2v-6zm4 0h-2v6h2v-6zm2-7h-2V2h-2v2H9V2H7v2H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V7c0-1.1-.9-2-2-2zM19 21H5V10h14v11z"/>
                                        </svg>
                                        <span><strong>Date de début:</strong> <c:out value="${entreprise.debutDateContrat != null ? '' : 'Non défini'}"/><fmt:formatDate value="${entreprise.debutDateContrat}" pattern="dd/MM/yyyy"/></span>
                                    </div>
                                    <div class="info-item">
                                        <svg class="info-icon" viewBox="0 0 24 24">
                                            <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/>
                                        </svg>
                                        <span><strong>Quartier:</strong> <c:out value="${entreprise.quartier != null ? entreprise.quartier : 'Non défini'}"/></span>
                                    </div>
                                    <div class="info-item">
                                        <svg class="info-icon" viewBox="0 0 24 24">
                                            <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/>
                                        </svg>
                                        <span><strong>Geom:</strong> <c:out value="${entreprise.geom != null ? entreprise.geom : 'NULL'}"/></span>
                                    </div>
                                </div>
                                <div class="entreprise-actions">
                                    <button class="btn btn-secondary btn-sm" onclick="viewOnMap(${entreprise.latitude != null ? entreprise.latitude : -18.8792}, ${entreprise.longitude != null ? entreprise.longitude : 47.5079}, '${entreprise.nom}')">🛰️ Voir sur la carte</button>
                                    <a href="${pageContext.request.contextPath}/entreprise/edit/${entreprise.id}" class="btn btn-primary btn-sm">✏️ Modifier</a>
                                    <a href="${pageContext.request.contextPath}/entreprise/delete/${entreprise.id}" class="btn btn-danger btn-sm" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette entreprise ?')">🗑️ Supprimer</a>
                                    <a href="${pageContext.request.contextPath}/mvtcontrat/list?entrepriseId=${entreprise.id}" class="btn btn-primary btn-sm">📋 Voir les Mouvements</a>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <script>
        const quartiers = [
            { nom: "Analakely", lat: -18.9087, lng: 47.5204 },
            { nom: "Antaninarenina", lat: -18.9143, lng: 47.5217 },
            { nom: "Tsaralalana", lat: -18.9089, lng: 47.5198 },
            { nom: "Ambohijatovo", lat: -18.9234, lng: 47.5289 },
            { nom: "Faravohitra", lat: -18.9012, lng: 47.5234 },
            { nom: "Ambohimiandra", lat: -18.8756, lng: 47.5123 },
            { nom: "Andavamamba", lat: -18.9167, lng: 47.5345 },
            { nom: "Ambodivona", lat: -18.8934, lng: 47.5456 },
            { nom: "Ambohipo", lat: -18.8823, lng: 47.5167 },
            { nom: "Ankazobe", lat: -18.9456, lng: 47.5678 },
            { nom: "Antanimena", lat: -18.9234, lng: 47.5123 },
            { nom: "Isotry", lat: -18.9345, lng: 47.5234 },
            { nom: "67Ha", lat: -18.9135, lng: 47.5450 },
            { nom: "Ankorondrano", lat: -18.8765, lng: 47.5267 },
            { nom: "Ivandry", lat: -18.8667, lng: 47.5312 },
            { nom: "Ambatobe", lat: -18.8652, lng: 47.5432 },
            { nom: "Andohalo", lat: -18.9108, lng: 47.5215 },
            { nom: "Ankatso", lat: -18.8990, lng: 47.5280 },
            { nom: "Ampasamadinika", lat: -18.9250, lng: 47.5317 },
            { nom: "Mahamasina", lat: -18.9225, lng: 47.5165 },
            { nom: "Anosy", lat: -18.9201, lng: 47.5164 },
            { nom: "Soarano", lat: -18.9051, lng: 47.5169 },
            { nom: "Ambanidia", lat: -18.9074, lng: 47.5304 },
            { nom: "Anosibe", lat: -18.9267, lng: 47.5220 },
            { nom: "Ampefiloha", lat: -18.9149, lng: 47.5275 },
            { nom: "Ankazomanga", lat: -18.8995, lng: 47.5500 },
            { nom: "Anosivavaka", lat: -18.9333, lng: 47.5333 },
            { nom: "Sabotsy Namehana", lat: -18.8350, lng: 47.5700 },
            { nom: "Ilafy", lat: -18.8567, lng: 47.5623 },
            { nom: "Mahazo", lat: -18.8789, lng: 47.5670 },
            { nom: "Ambohimangakely", lat: -18.8700, lng: 47.6000 },
            { nom: "Andraharo", lat: -18.8892, lng: 47.5255 },
            { nom: "Antohomadinika", lat: -18.9141, lng: 47.5281 },
            { nom: "Anjanahary", lat: -18.8993, lng: 47.5078 },
            { nom: "Ampandrana", lat: -18.9100, lng: 47.5270 },
            { nom: "Ambatoroka", lat: -18.8842, lng: 47.5308 },
            { nom: "Ankadifotsy", lat: -18.9083, lng: 47.5282 },
            { nom: "Ankadikely", lat: -18.8362, lng: 47.5791 },
            { nom: "Anjanamasina", lat: -18.8760, lng: 47.5425 },
            { nom: "Anosizato", lat: -18.9269, lng: 47.5001 },
            { nom: "Antsobolo", lat: -18.9110, lng: 47.5225 },
            { nom: "Ampitatafika", lat: -18.9543, lng: 47.4682 },
            { nom: "Manjakaray", lat: -18.8828, lng: 47.5281 },
            { nom: "Ambohitrimanjaka", lat: -18.8581, lng: 47.4453 },
            { nom: "Tanjombato", lat: -18.9547, lng: 47.5132 },
            { nom: "Ankadindramamy", lat: -18.8642, lng: 47.5365 },
            { nom: "Amboditsiry", lat: -18.8912, lng: 47.5334 },
            { nom: "Ambolokandrina", lat: -18.9184, lng: 47.5290 },
            { nom: "Ankerana", lat: -18.8785, lng: 47.5702 },
            { nom: "Fidasiana", lat: -18.9102, lng: 47.5338 }
        ];

        let map;
        let marker;

        function initMap(lat = -18.8792, lng = 47.5079, zoom = 12) {
            if (map) {
                map.remove();
            }
            map = L.map('map').setView([lat, lng], zoom);
            L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}', {
                attribution: 'Tiles © Esri — Source: Esri, i-cubed, USDA, USGS, AEX, GeoEye, Getmapping, Aerogrid, IGN, IGP, UPR-EGP, and the GIS User Community',
                maxZoom: 18
            }).addTo(map);

            map.on('click', function(e) {
                placeMarker(e.latlng);
            });

            const latInput = document.getElementById('latitude').value;
            const lngInput = document.getElementById('longitude').value;
            if (latInput && lngInput && !isNaN(latInput) && !isNaN(lngInput)) {
                placeMarker({ lat: parseFloat(latInput), lng: parseFloat(lngInput) });
                map.setView([parseFloat(latInput), parseFloat(lngInput)], 16);
                highlightQuartier(latInput, lngInput);
            }
        }

        function placeMarker(latlng) {
            if (marker) {
                marker.remove();
            }
            marker = L.marker(latlng).addTo(map)
                .bindPopup(`Coordonnées: (${latlng.lat.toFixed(6)}, ${latlng.lng.toFixed(6)})`);
            document.getElementById('latitude').value = latlng.lat.toFixed(6);
            document.getElementById('longitude').value = latlng.lng.toFixed(6);
            highlightQuartier(latlng.lat, latlng.lng);

            marker.on('mouseover', function() {
                marker.openPopup();
            });
            marker.on('mouseout', function() {
                marker.closePopup();
            });
        }

        function toggleQuartierList() {
            const list = document.getElementById('quartier-list');
            list.classList.toggle('show');
        }

        function generateQuartierList() {
            const list = document.getElementById('quartier-list');
            list.innerHTML = '';
            quartiers.forEach(quartier => {
                const item = document.createElement('div');
                item.className = 'quartier-list-item';
                item.textContent = quartier.nom;
                item.onclick = () => {
                    selectQuartier(quartier, item);
                    toggleQuartierList();
                };
                if (quartier.nom === document.getElementById('quartier').value) {
                    item.classList.add('selected');
                }
                list.appendChild(item);
            });
        }

        function highlightQuartier(lat, lng) {
            const tolerance = 0.01;
            const quartier = quartiers.find(q => 
                Math.abs(q.lat - lat) < tolerance && Math.abs(q.lng - lng) < tolerance
            );
            document.querySelectorAll('.quartier-list-item').forEach(item => item.classList.remove('selected'));
            if (quartier) {
                document.querySelectorAll('.quartier-list-item').forEach(item => {
                    if (item.textContent === quartier.nom) {
                        item.classList.add('selected');
                    }
                });
                document.getElementById('quartier').value = quartier.nom;
                document.getElementById('selected-quartier').textContent = quartier.nom;
            } else {
                document.getElementById('quartier').value = '';
                document.getElementById('selected-quartier').textContent = 'Choisir un quartier';
            }
        }

        function viewOnMap(lat, lng, nom) {
            console.log(`viewOnMap called with lat=${lat}, lng=${lng}, nom=${nom}`);
            showTab('create');
            setTimeout(() => {
                initMap(lat, lng, 16);
                placeMarker({ lat, lng });
                highlightQuartier(lat, lng);
            }, 500);
        }

        function selectQuartier(quartier, element) {
            document.querySelectorAll('.quartier-list-item').forEach(item => item.classList.remove('selected'));
            element.classList.add('selected');
            document.getElementById('latitude').value = quartier.lat.toFixed(6);
            document.getElementById('longitude').value = quartier.lng.toFixed(6);
            document.getElementById('quartier').value = quartier.nom;
            document.getElementById('selected-quartier').textContent = quartier.nom;
            if (map) {
                map.setView([quartier.lat, quartier.lng], 16);
                placeMarker({ lat: quartier.lat, lng: quartier.lng });
            }
        }

        function showTab(tabName) {
            console.log(`Showing tab: ${tabName}`);
            document.querySelectorAll('.tab-button').forEach(btn => btn.classList.remove('active'));
            document.querySelectorAll('.tab-content').forEach(content => content.classList.remove('active'));
            document.getElementById(tabName + '-tab').classList.add('active');
            document.querySelector(`.tab-button[onclick="showTab('${tabName}')"]`).classList.add('active');
            if (tabName === 'create') {
                setTimeout(() => {
                    const lat = parseFloat(document.getElementById('latitude').value) || -18.8792;
                    const lng = parseFloat(document.getElementById('longitude').value) || 47.5079;
                    initMap(lat, lng, 16);
                    if (document.getElementById('latitude').value && document.getElementById('longitude').value) {
                        placeMarker({ lat, lng });
                    }
                }, 100);
            }
        }

        function validateForm() {
            const nom = document.getElementById('nom').value;
            const adresse = document.getElementById('adresse').value;
            const latitude = document.getElementById('latitude').value;
            const longitude = document.getElementById('longitude').value;
            const debutDateContrat = document.getElementById('debutDateContrat').value;
            let errorMessage = '';

            if (!nom || nom.trim() === '') {
                errorMessage += 'Le nom de l\'entreprise est requis.\n';
            }
            if (!adresse || adresse.trim() === '') {
                errorMessage += 'L\'adresse est requise.\n';
            }
            if (latitude && (isNaN(latitude) || parseFloat(latitude) < -90 || parseFloat(latitude) > 90)) {
                errorMessage += 'La latitude doit être un nombre valide entre -90 et 90.\n';
            }
            if (longitude && (isNaN(longitude) || parseFloat(longitude) < -180 || parseFloat(longitude) > 180)) {
                errorMessage += 'La longitude doit être un nombre valide entre -180 et 180.\n';
            }
            if (latitude && !longitude || !latitude && longitude) {
                errorMessage += 'Veuillez fournir à la fois la latitude et la longitude, ou aucune des deux.\n';
            }
            if (debutDateContrat && !/^\d{4}-\d{2}-\d{2}$/.test(debutDateContrat)) {
                errorMessage += 'La date de début du contrat doit être au format AAAA-MM-JJ.\n';
            }

            if (errorMessage) {
                console.error('Validation errors:', errorMessage);
                alert(errorMessage);
                return false;
            }
            return true;
        }

        document.addEventListener('DOMContentLoaded', () => {
            generateQuartierList();
            initMap();
            document.getElementById('entreprise-form').addEventListener('submit', (e) => {
                if (!validateForm()) {
                    e.preventDefault();
                }
            });
            document.addEventListener('click', (e) => {
                const dropdown = document.querySelector('.quartier-dropdown');
                const list = document.getElementById('quartier-list');
                if (!dropdown.contains(e.target) && list.classList.contains('show')) {
                    list.classList.remove('show');
                }
            });
        });
    </script>
</body>
</html>