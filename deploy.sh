#!/bin/bash

# === Configuration ===
WAR_NAME="VelonKan.war"
SOURCE_DIR="."
DEST_DIR="/opt/tomcat9/webapps"

# === Copying WAR file ===
echo "Copying $WAR_NAME to $DEST_DIR ..."
cp -f "$SOURCE_DIR/$WAR_NAME" "$DEST_DIR/$WAR_NAME"

# === Check if copy was successful ===
if [ $? -eq 0 ]; then
    echo "Deploy successful."
else
    echo "Deploy failed."
    exit 1
fi