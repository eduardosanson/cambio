build-image:
	 docker build . -t sanson/cambio:0.0.1

push-image:
	docker push sanson/cambio:0.0.1