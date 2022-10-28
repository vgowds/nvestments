package com.nvestment;

import com.google.gson.Gson;
import java.util.HashMap;
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
        logger.info(input.getBody());
        System.out.println("INPUT BODY - START");
        System.out.println(input.getBody());
        System.out.println("INPUT BODY - END");
        Gson gson = new Gson();
        Fund bodyInput = gson.fromJson(input.getBody(), Fund.class);

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