package helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.KafkaEvent;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<KafkaEvent, String> {
    @Override
    public String handleRequest(KafkaEvent kafkaEvent, Context context) {
        LambdaLogger logger = context.getLogger();
        System.out.println("---------------App kafka consumer---------------------");
        Map<String, List<KafkaEvent.KafkaEventRecord>> eventRecordListMap = kafkaEvent.getRecords();


        System.out.println("---------------App kafka consumer end---------------------");
        return "200 OK";
    }
}