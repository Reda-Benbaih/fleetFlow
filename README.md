#Pour construire et démarrer l’application avec Docker :

.\mvnw.cmd clean package -DskipTests
docker compose up --build

#Pour arrêter les conteneurs :

docker compose down

#Une fois l’application démarrée, tu peux vérifier qu’elle fonctionne via Swagger :

http://localhost:8082/swagger-ui/index.html