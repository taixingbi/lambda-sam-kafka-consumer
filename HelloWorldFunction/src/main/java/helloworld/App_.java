package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class App_ implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    KafkaSubscribe kafkaSubscribe= new KafkaSubscribe();
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        LambdaLogger logger = context.getLogger();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        System.out.println("\n---------------------------App-----------------------------");
        logger.log("\n---------------------------App-----------------------------");

//        kafkaSubscribe.receive(logger);

        System.out.println("---------------------------App end-----------------------------\n");
        logger.log("---------------------------App end-----------------------------\n");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        return response
                .withStatusCode(200)
                .withBody("hi saks, kafka consumer ok");
    }
}
