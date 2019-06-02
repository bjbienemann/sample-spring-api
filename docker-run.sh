docker rm -f api

docker rmi api:latest

docker build -t api .

# --restart always 
docker run --name api --link api-db:PSQL -p 8880:8080 -d api