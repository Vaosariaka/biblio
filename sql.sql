CREATE TABLE personne (
  personne_id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  date_naissance DATE NOT NULL,
  type ENUM('adherent','bibliothecaire') NOT NULL,
  login VARCHAR(100) UNIQUE NOT NULL,
  mot_de_passe VARCHAR(255) NOT NULL
);

CREATE TABLE adherent (
  personne_id INT PRIMARY KEY,
  categorie ENUM('etudiant','professionnel','professeur') NOT NULL,
  date_debut DATE NOT NULL,
  date_fin DATE NOT NULL,
  quota_prets INT NOT NULL DEFAULT 0,
  quota_prolongations INT NOT NULL DEFAULT 0,
  FOREIGN KEY (personne_id) REFERENCES personne(personne_id)
    ON DELETE CASCADE
);

CREATE TABLE bibliothecaire (
  personne_id INT PRIMARY KEY,
  FOREIGN KEY (personne_id) REFERENCES personne(personne_id)
    ON DELETE CASCADE
);

CREATE TABLE genreLivre (
  genre_id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE auteur (
  auteur_id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(200) NOT NULL
);

CREATE TABLE livre (
  livre_id INT AUTO_INCREMENT PRIMARY KEY,
  titre VARCHAR(255) NOT NULL,
  id_genre INT NOT NULL,
  id_auteur INT NOT NULL,
  categorie VARCHAR(50),
  nombre_exemplaires INT NOT NULL DEFAULT 0,
  FOREIGN KEY (id_genre) REFERENCES genreLivre(genre_id),
  FOREIGN KEY (id_auteur) REFERENCES auteur(auteur_id)
);

CREATE TABLE exemplaire (
  exemplaire_id INT AUTO_INCREMENT PRIMARY KEY,
  id_livre INT NOT NULL,
  statut ENUM('Disponible','Emprunte','Reserve') NOT NULL DEFAULT 'Disponible',
  FOREIGN KEY (id_livre) REFERENCES livre(livre_id)
);

CREATE TABLE pret (
  pret_id INT AUTO_INCREMENT PRIMARY KEY,
  id_adherent INT NOT NULL,
  id_exemplaire INT NOT NULL,
  date_pret DATE NOT NULL,
  date_retour DATE NOT NULL,
  nb_prolongations INT NOT NULL DEFAULT 0,
  FOREIGN KEY (id_adherent) REFERENCES adherent(personne_id),
  FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(exemplaire_id)
);

CREATE TABLE reservation (
  reservation_id INT AUTO_INCREMENT PRIMARY KEY,
  id_adherent INT NOT NULL,
  id_exemplaire INT NOT NULL,
  date_reservation DATE NOT NULL,
  date_pret DATE,
  FOREIGN KEY (id_adherent) REFERENCES adherent(personne_id),
  FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(exemplaire_id)
);

CREATE TABLE penalite (
  penalite_id INT AUTO_INCREMENT PRIMARY KEY,
  id_adherent INT NOT NULL,
  date_debut DATE NOT NULL, -- Date à laquelle la pénalité commence (ex: date de retour en retard)
  nbre_jours_sanction INT NOT NULL, -- Nombre de jours de sanction (ex: 5 jours)
  FOREIGN KEY (id_adherent) REFERENCES adherent(personne_id)
);


--pour voir date_fin penalite
SELECT 
  date_debut, 
  nbre_jours_sanction,
  DATE_ADD(date_debut, INTERVAL nbre_jours_sanction DAY) AS date_fin
FROM penalite
WHERE id_adherent = ?;


CREATE TABLE quota (
  id_adherent INT PRIMARY KEY,
  quota_prets_restants INT NOT NULL DEFAULT 0,
  quota_prolongations_restants INT NOT NULL DEFAULT 0,
  FOREIGN KEY (id_adherent) REFERENCES adherent(personne_id)
);


CREATE TABLE jours_feries (
  date DATE PRIMARY KEY,
  description VARCHAR(255)
);




