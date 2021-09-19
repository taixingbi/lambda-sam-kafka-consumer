sam package --s3-bucket s3-simple-lambda --output-template-file out_consumer1.yaml
sam deploy --template-file out_consumer1.yaml --capabilities CAPABILITY_IAM --stack-name MyStackConsumer1