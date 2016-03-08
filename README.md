# Microservice Seed for Akka HTTP
Developer seed, making it easy to get started developing with:
- Akka
- Akka HTTP
- Akka HTTP Testkit
- Service test
- Unit test
- Docker
- CircleCI (with continuous integration/deployment)
- Elastic Beanstalk (AWS)
- Scala Test

## OSX Docker Set Up

One time setup:
install boot2docker then:

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

## Deployment
The circle file is setup to deploy automatically to staging area. But for this to work you have to setup the application and environment on AWS Elastic Beanstalk (you can use the AWS console). Then configure EB_BUCKET, EB_APPLICATION_NAME and EB_ENVIRONMENT_NAME in sh/deploy.sh script.
 
#### To deploy from local
One time setup:
	
	cd docker
	eb init (then select environment etc...)
	
Then

	make deploy-s
	or
	make deploy-p


## Makefile
	-test 
	-test-u (unit)
	-test-s (service)
	-run-l (run local)
	-build (builds a artifect and place it in the docker folder and after that build the docker container)
	-deploy-s (deploy on staging)
	-test-s-s (service tests ageins staging)
	-deploy-p (deploy in production)


## Troubleshooting
This version of akka is logging a error when dropping a TLS connection:

    [ERROR] [03/08/2016 16:10:27.036] [default-akka.actor.default-dispatcher-2] [akka://default/user/StreamSupervisor-0/flow-1-0-unknown-operation] Error in stage [recover]: No elements passed in the last 1 minute. (akka.http.impl.engine.HttpConnectionTimeoutException)
    [DEBUG] [03/08/2016 16:10:27.037] [default-akka.actor.default-dispatcher-2] [akka://default/user/StreamSupervisor-0/flow-1-0-unknown-operation] Aborting tcp connection because of upstream failure: No elements passed in the last 1 minute.

Don't worry about this. Should only be a DEBUG entry on next version

## Resources
Some good resources:
- [Iterators: akka-http-microservice](https://github.com/theiterators/akka-http-microservice "akka-http-microservice")
- [Iterators: reactive-microservices](https://github.com/theiterators/reactive-microservices "reactive-microservices")
- [Akka Docs](http://akka.io/docs/ "akka docs")