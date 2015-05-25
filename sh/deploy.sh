#! /bin/bash

SHA1=$1

cd docker
# Create new Elastic Beanstalk version; your bucket
EB_BUCKET=elasticbeanstalk-us-west-1-4324314318

zip $CIRCLE_ARTIFACTS/microservice Dockerfile Dockerrun.aws.json microservice.jar

aws s3 cp $CIRCLE_ARTIFACTS/microservice.zip s3://$EB_BUCKET/microservice/microservice-$SHA1.zip
aws elasticbeanstalk create-application-version --application-name microservice --version-label $SHA1 --source-bundle S3Bucket=$EB_BUCKET,S3Key=microservice/microservice-$SHA1.zip

# Update Elastic Beanstalk environment to new version
aws elasticbeanstalk update-environment --environment-name microservice-staging --version-label $SHA1