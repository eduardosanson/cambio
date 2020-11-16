build-image:
	 docker build . -t sanson/cambio:0.0.2

push-image:
	docker push sanson/cambio:0.0.2

redis-start:
	docker-compose up -d

run-local: redis-start
	./mvnw spring-boot:run -Drun.profiles=local