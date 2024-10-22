echo "----------------PARANDO CONTAINERS-------------"
docker compose stop

echo "----------------BUILDAR E RODAR TESTES-------------"
mvn clean install

echo "----------------BUILDAR CONTAINERS-------------"
docker compose build

echo "----------------SUBIR CONTAINERS-------------"
docker compose up -d
