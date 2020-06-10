package com.soap.controllers;

import com.soap.model.FindPlaceMonumentsRequest;
import com.soap.model.FindPlaceMonumentsResponse;
import com.soap.services.FindPlaceMonumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
/**
 * @author Grigorios Ladas
 */
@Endpoint
public class FindPlaceMonumentsController {

    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final FindPlaceMonumentsService findPlaceMonumentsService;


    @Autowired
    public FindPlaceMonumentsController(FindPlaceMonumentsService findTownMonumentsService) {
        this.findPlaceMonumentsService = findTownMonumentsService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findPlaceMonumentsRequest")
    @ResponsePayload
    public FindPlaceMonumentsResponse findTownMonumentsResponse(@RequestPayload FindPlaceMonumentsRequest request) {
        return findPlaceMonumentsService.findPlaceMonumentsResponse(request);
    }


}
