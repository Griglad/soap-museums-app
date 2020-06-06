package com.soap.controllers;

import com.soap.model.FindCountryMonumentsRequest;
import com.soap.model.FindCountryMonumentsResponse;
import com.soap.services.FindCountryMonumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class FindCountryMonumentsController {


    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final FindCountryMonumentsService findCountryMonumentsService;


    @Autowired
    public FindCountryMonumentsController(FindCountryMonumentsService findCountryMonumentsService) {
        this.findCountryMonumentsService = findCountryMonumentsService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findCountryMonumentsRequest")
    @ResponsePayload
    public FindCountryMonumentsResponse findCountryMonumentsResponse(@RequestPayload FindCountryMonumentsRequest request) {
        return findCountryMonumentsService.findCountryMonumentsResponse(request);
    }


}
