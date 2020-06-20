package com.soap.controllers;

import com.soap.model.FindNamesLargerInputValueRequest;
import com.soap.model.FindNamesLargerInputValueResponse;
import com.soap.services.FindMuseumsNamesLargerInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class FindMuseumsNamesLargerInputController {

    private static final String NAMESPACE_URI = "http://www.soap.com/model";

    private final FindMuseumsNamesLargerInputService findMuseumsNamesLargerInputService;

    @Autowired
    public FindMuseumsNamesLargerInputController(FindMuseumsNamesLargerInputService findMuseumsNamesLargerInputService) {
        this.findMuseumsNamesLargerInputService = findMuseumsNamesLargerInputService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findNamesLargerInputValueRequest")
    @ResponsePayload
    public FindNamesLargerInputValueResponse getPointsLargerResponse(@RequestPayload FindNamesLargerInputValueRequest request) {


        return findMuseumsNamesLargerInputService.getPointsLargerResponse(request);
    }


}
