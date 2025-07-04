<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${adherent.personneId == 0 ? 'Ajouter' : 'Modifier'} un Adhérent</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">${adherent.personneId == 0 ? 'Ajouter un Adhérent' : 'Modifier un Adhérent'}</h1>
        <form action="${adherent.personneId == 0 ? '/biblio/adherents/save' : '/biblio/adherents/update/' + adherent.personneId}" method="post">
            <div class="mb-3">
                <label for="personneId" class="form-label">ID Personne</label>
                <input type="number" class="form-control" id="personneId" name="personneId" value="${adherent.personneId}" required>
            </div>
            <div class="mb-3">
                <label for="categorie" class="form-label">Catégorie</label>
                <select class="form-control" id="categorie" name="categorie" required>
                    <option value="etudiant" ${adherent.categorie == 'etudiant' ? 'selected' : ''}>Étudiant</option>
                    <option value="professionnel" ${adherent.categorie == 'professionnel' ? 'selected' : ''}>Professionnel</option>
                    <option value="professeur" ${adherent.categorie == 'professeur' ? 'selected' : ''}>Professeur</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="dateDebut" class="form-label">Date début</label>
                <input type="date" class="form-control" id="dateDebut" name="dateDebut" value="${adherent.dateDebut}" required>
            </div>
            <div class="mb-3">
                <label for="dateFin" class="form-label">Date fin</label>
                <input type="date" class="form-control" id="dateFin" name="dateFin" value="${adherent.dateFin}" required>
            </div>
            <div class="mb-3">
                <label for="quotaPrets" class="form-label">Quota Prêts</label>
                <input type="number" class="form-control" id="quotaPrets" name="quotaPrets" value="${adherent.quotaPrets}" required>
            </div>
            <div class="mb-3">
                <label for="quotaProlongations" class="form-label">Quota Prolongations</label>
                <input type="number" class="form-control" id="quotaProlongations" name="quotaProlongations" value="${adherent.quotaProlongations}" required>
            </div>
            <button type="submit" class="btn btn-primary">Enregistrer</button>
            <a href="/biblio/adherents" class="btn btn-secondary">Annuler</a>
        </form>
    </div>
</body>
</html>
