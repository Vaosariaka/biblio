-- 1. Créer les types ENUM
CREATE TYPE type_personne AS ENUM ('adherent', 'bibliothecaire');
CREATE TYPE categorie_adherent AS ENUM ('etudiant', 'professionnel', 'professeur');
CREATE TYPE statut_exemplaire AS ENUM ('Disponible', 'Emprunte', 'Reserve');
CREATE TYPE categorie_age AS ENUM ('Moins de 18 ans', 'Plus de 18 ans');


-- 2. Table personne
CREATE TABLE personne (
  personne_id SERIAL PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  date_naissance DATE NOT NULL,
  type type_personne NOT NULL,
  login VARCHAR(100) UNIQUE NOT NULL,
  mot_de_passe VARCHAR(255) NOT NULL
);

-- 3. Table adherent
CREATE TABLE adherent (
  personne_id INT PRIMARY KEY,
  categorie categorie_adherent NOT NULL,
  date_debut DATE NOT NULL,
  date_fin DATE NOT NULL,
  quota_prets INT NOT NULL DEFAULT 0,
  quota_prolongations INT NOT NULL DEFAULT 0,
  FOREIGN KEY (personne_id) REFERENCES personne(personne_id) ON DELETE CASCADE
);

-- 4. Table bibliothecaire
CREATE TABLE bibliothecaire (
  personne_id INT PRIMARY KEY,
  FOREIGN KEY (personne_id) REFERENCES personne(personne_id) ON DELETE CASCADE
);

-- 5. Table Genre
CREATE TABLE Genre (
  Genre_id SERIAL PRIMARY KEY,
  nom VARCHAR(100) NOT NULL UNIQUE
);

-- 6. Table auteur
CREATE TABLE auteur (
  auteur_id SERIAL PRIMARY KEY,
  nom VARCHAR(200) NOT NULL
);

-- 7. Table livre
CREATE TABLE livre (
  livre_id SERIAL PRIMARY KEY,
  titre VARCHAR(255) NOT NULL,
  id_Genre INT NOT NULL,
  id_auteur INT NOT NULL,
  categorie categorie_age, -- Remplacement de VARCHAR(50) par categorie_age
  nombre_exemplaires INT NOT NULL DEFAULT 0,
  FOREIGN KEY (id_Genre) REFERENCES Genre(Genre_id),
  FOREIGN KEY (id_auteur) REFERENCES auteur(auteur_id)
);

-- 8. Table exemplaire
CREATE TABLE exemplaire (
  exemplaire_id SERIAL PRIMARY KEY,
  id_livre INT NOT NULL,
  statut statut_exemplaire NOT NULL DEFAULT 'Disponible',
  FOREIGN KEY (id_livre) REFERENCES livre(livre_id)
);

-- 9. Table pret
CREATE TABLE pret (
  pret_id SERIAL PRIMARY KEY,
  id_adherent INT NOT NULL,
  id_exemplaire INT NOT NULL,
  date_pret DATE NOT NULL,
  date_retour DATE NOT NULL,
  nb_prolongations INT NOT NULL DEFAULT 0,
  FOREIGN KEY (id_adherent) REFERENCES adherent(personne_id),
  FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(exemplaire_id)
);

-- 10. Table reservation
CREATE TABLE reservation (
  reservation_id SERIAL PRIMARY KEY,
  id_adherent INT NOT NULL,
  id_exemplaire INT NOT NULL,
  date_reservation DATE NOT NULL,
  date_pret DATE,
  FOREIGN KEY (id_adherent) REFERENCES adherent(personne_id),
  FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(exemplaire_id)
);

-- 11. Table penalite
CREATE TABLE penalite (
  penalite_id SERIAL PRIMARY KEY,
  id_adherent INT NOT NULL,
  date_debut DATE NOT NULL,
  nbre_jours_sanction INT NOT NULL,
  FOREIGN KEY (id_adherent) REFERENCES adherent(personne_id)
);

-- 12. Requête PostgreSQL pour voir la date de fin de pénalité
-- Utilise l'opérateur + INTERVAL
-- Exemple (à utiliser dans une vue ou requête préparée en application)
-- Remplace `?` par la valeur souhaitée en application
SELECT 
  date_debut, 
  nbre_jours_sanction,
  date_debut + (nbre_jours_sanction || ' days')::interval AS date_fin
FROM penalite
WHERE id_adherent = 1;

-- 13. Table quota
CREATE TABLE quota (
  id_adherent INT PRIMARY KEY,
  quota_prets_restants INT NOT NULL DEFAULT 0,
  quota_prolongations_restants INT NOT NULL DEFAULT 0,
  FOREIGN KEY (id_adherent) REFERENCES adherent(personne_id)
);

-- 14. Table jours fériés
CREATE TABLE jours_feries (
  date DATE PRIMARY KEY,
  description VARCHAR(255)
);
