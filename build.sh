#!/bin/bash

# === Chemins ===
SRC="src/main/java/org/example"
WEBAPP="src/main/webapp"
RESOURCES="src/main/resources"
OUT="build"
LIB="lib"

# === Création du dossier de sortie ===
if [ ! -d "$OUT" ]; then
    mkdir -p "$OUT"
fi

# === Construction de la liste des fichiers Java ===
echo "Création de la liste des sources Java..."
rm -f sources.txt
find "$SRC" -type f -name "*.java" > sources.txt

# === Compilation ===
echo "Compilation des fichiers Java..."
javac -encoding UTF-8 -source 17 -target 17 -cp "$LIB/*" -d "$OUT/WEB-INF/classes" @sources.txt

if [ $? -ne 0 ]; then
    echo "Erreur de compilation."
    exit $?
fi

echo "Compilation réussie."

# === Copie des ressources web ===
echo "Copie des fichiers web..."
cp -r "$WEBAPP/"* "$OUT/"

# === Copie des fichiers de ressources ===
echo "Copie des fichiers de ressources..."
if [ -d "$RESOURCES" ]; then
    cp -r "$RESOURCES/"* "$OUT/WEB-INF/classes/"
else
    echo "Attention: le dossier de ressources $RESOURCES n'existe pas!"
fi

# === Copie des libs dans WEB-INF/lib ===
echo "Copie des bibliothèques..."
mkdir -p "$OUT/WEB-INF/lib"
cp "$LIB/"*.jar "$OUT/WEB-INF/lib/"

# === Création du fichier WAR ===
echo "Création du WAR..."
cd "$OUT"
jar -cvf ../biblio.war .
cd ..

echo "WAR généré avec succès : biblio.war"
echo "Vérifiez que application.properties est bien présent dans WEB-INF/classes"