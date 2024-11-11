




FROM openjdk:17-jdk-alpine AS downloader

# Installer curl
RUN apk add --no-cache curl

# Définir l'URL du JAR dans une variable d'environnement
ENV JAR_URL=http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/1.0/5SE4G3gestionstationski-1.0.jar

# Télécharger le JAR depuis Nexus
RUN curl -u admin:root -o /5SE4G3gestionstationski-1.0.jar $JAR_URL

# Étape 2: Construire l'image finale
FROM openjdk:17-jdk-alpine
EXPOSE 8089

# Copier le JAR téléchargé depuis l'étape downloader
COPY --from=downloader /5SE4G3gestionstationski-1.0.jar 5SE4G3gestionstationski-1.0.jar

ENTRYPOINT ["java", "-jar", "/5SE4-G3-gestion-station-ski-1.0.jar"]