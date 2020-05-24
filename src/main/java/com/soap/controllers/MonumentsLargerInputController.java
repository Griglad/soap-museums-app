package com.soap.controllers;


import com.soap.model.GetNamesLargerInputValueRequest;
import com.soap.model.GetNamesLargerInputValueResponse;
import com.soap.services.MonumentsLargerInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MonumentsLargerInputController {

    private static final String NAMESPACE_URI = "http://www.soap.com/model";

    private MonumentsLargerInputService monumentsLargerInputService;

    @Autowired
    public MonumentsLargerInputController(MonumentsLargerInputService monumentsLargerInputService) {
        this.monumentsLargerInputService = monumentsLargerInputService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNamesLargerInputValueRequest")
    @ResponsePayload
    public GetNamesLargerInputValueResponse getPointsLargerResponse(@RequestPayload GetNamesLargerInputValueRequest request) {


        return monumentsLargerInputService.getPointsLargerResponse(request);
    }


}
