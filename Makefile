test:
	sbt test

test-s:
	sbt "testOnly com.cluda.*Spec"

test-u:
	sbt "testOnly com.cluda.*Test"

run-l:
	docker run -p 8888:8888 --rm -it cluda/microservice

build:
	sbt assembly
	docker build -t cluda/microservice docker/

deploy-s:
	cd docker; eb use microservice-staging; eb deploy;

test-s-s:

deploy-p:
	cd docker; eb use microservice; eb deploy;

setup-db: