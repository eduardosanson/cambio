build-image:
	 docker build . -t sanson/cambio:0.0.2

push-image:
	docker push sanson/cambio:0.0.2