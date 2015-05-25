#! /bin/bash

SHA1=$1

cd docker

EB_BUCKET=elasticbeanstalk-us-west-1-4324314318
EB_APPLICATION_NAME=microservice
EB_ENVIRONMENT_NAME=microservice-staging
MICROSERVICE_FILE_NAME=microservice-file #watever you want

zip $CIRCLE_ARTIFACTS/$MICROSERVICE_FILE_NAME Dockerfile Dockerrun.aws.json microservice.jar

aws s3 cp $CIRCLE_ARTIFACTS/$MICROSERVICE_FILE_NAME.zip s3://$EB_BUCKET/$EB_APPLICATION_NAME/$MICROSERVICE_FILE_NAME-$SHA1.zip
aws elasticbeanstalk create-application-version --application-name $EB_APPLICATION_NAME --version-label $SHA1 --source-bundle S3Bucket=$EB_BUCKET,S3Key=$EB_APPLICATION_NAME/$MICROSERVICE_FILE_NAME-$SHA1.zip

# Update Elastic Beanstalk environment to new version
aws elasticbeanstalk update-environment --environment-name $EB_ENVIRONMENT_NAME --version-label $SHA1