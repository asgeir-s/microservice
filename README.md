# Microservice Seed for Akka HTTP

- Akka
- Akka HTTP
- Akka HTTP Testkit
- Service test
- Unit test
- Docker
- CircleCI (with continuous deployment)
- Elastic Beanstalk (AWS)
- Scala Test

## OSX Docker Set Up
Local if when runing docker: localhost

One time setup:

	boot2docker init
	VBoxManage modifyvm "boot2docker-vm" --natpf1 "postgres-port,tcp,127.0.0.1,5432,,5432" #osx specific bind (local) # set postgres to "listen on *" and "host all all 0.0.0.0/0 trust"

Setup on each shell:

	boot2docker start
	eval "$(boot2docker shellinit)"
	
## Run

	make test   => for running all tests
	make build  => for building jar and Docker image
	make run-l  => for running docker-image locally
	
	sbt run 	=> running application locally without Docker
	
available addresses:

	http://localhost:8888/ping
	http://localhost:8888/route/watever/deep

##### Deployment
One time setup:
	
	cd docker
	eb init (then select environment etc...)

## Makefile
	-test 
	-test-u (unit)
	-test-s (service)
	-run-l (run local)
	-build (builds a artifect and place it in the docker folder and afther that build the docker container)
	-deploy-s (deploy on staging)
	-test-s-s (service tests ageins staging)
	-deploy-p (deploy in production)