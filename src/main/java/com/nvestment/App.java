package com.nvestment;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    static Logger logger = LoggerFactory.getLogger(App.class);

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {

        Gson gson = new Gson();
        Fund bodyInput = null;
        logger.info("input.getHttpMethod() "+input.getHttpMethod());
        if(input.getHttpMethod().equals("POST") ){
            logger.info("POST METHOD");
            bodyInput = gson.fromJson(input.getBody(), Fund.class);
            logger.info("POST METHOD "+bodyInput.getSymbol());
        } else if (input.getHttpMethod().equals("GET")) {
            logger.info("GET METHOD ...");
            Map<String, List<String>> inputParams = input.getMultiValueQueryStringParameters();
            for (Map.Entry<String, List<String>> entry : inputParams.entrySet()) {
                List<String> valueList = entry.getValue();
                logger.info("List value "+valueList.toString());
                for (String valueStr : valueList) {
                    bodyInput = new Fund();
                    bodyInput.setSymbol(valueStr);
                }
            }
            logger.info("GET METHOD "+bodyInput.getSymbol());

        }else{
            bodyInput = new Fund();
            bodyInput.setSymbol("NO_FOUND");
            logger.info("UNKNOWN METHOD "+bodyInput.getSymbol());
        }

        String result = getFundDetails(bodyInput.getSymbol());
        String output = String.format("{ \"result\": %s }", result);

        Map<String, String> responseHeaders = new HashMap<>();
        responseHeaders.put("Content-Type", "application/json");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent().withHeaders(responseHeaders);

        return response
                .withStatusCode(200)
                .withBody(output);
    }

    private String getFundDetails(String symbol) {
        
        Fund returnFundDetails = new Fund();
        if(symbol.equals("FAARX")){
            returnFundDetails.setSymbol("FAARX");
            returnFundDetails.setCompanyName("Nuveen All-American Municipal Bond Fund Class I");
            returnFundDetails.setPrice("9.57");
            returnFundDetails.setPriceDate("10/27/2022");
        } else if (symbol.equals("NHMRX")) {
            returnFundDetails.setSymbol("NHMRX");
            returnFundDetails.setCompanyName("Nuveen High Yield Municipal Bond Fund Class I");
            returnFundDetails.setPrice("13.97");
            returnFundDetails.setPriceDate("10/27/2022");
        } else if (symbol.equals("NUVBX")) {
            returnFundDetails.setSymbol("NUVBX");
            returnFundDetails.setCompanyName("Nuveen Intermediate Duration Municipal Bond Fund Class I");
            returnFundDetails.setPrice("8.37");
            returnFundDetails.setPriceDate("10/27/2022");
        }else{
            returnFundDetails.setSymbol(symbol);
            returnFundDetails.setCompanyName("NO DATA");
            returnFundDetails.setPrice("NO DATA");
            returnFundDetails.setPriceDate("10/27/2022");

        }
        return  returnFundDetails.toString();
    }
}