sam package --s3-bucket s3-simple-lambda --output-template-file out.yaml
sam deploy --template-file out.yaml --capabilities CAPABILITY_IAM --stack-name MyStackConsumer