package com.soap.controllers;

import com.soap.model.FindByPlaceMuseumsRequest;
import com.soap.model.FindByPlaceMuseumsResponse;
import com.soap.services.FindByPlaceMuseumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
/**
 * @author Grigorios Ladas
 */
@Endpoint
public class FindByPlaceMuseumsController {

    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final FindByPlaceMuseumsService findByPlaceMuseumsService;


    @Autowired
    public FindByPlaceMuseumsController(FindByPlaceMuseumsService findByPlaceMuseumsService) {
        this.findByPlaceMuseumsService = findByPlaceMuseumsService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findByPlaceMuseumsRequest")
    @ResponsePayload
    public FindByPlaceMuseumsResponse findByPlaceMuseumsResponse(@RequestPayload FindByPlaceMuseumsRequest request) {
        return findByPlaceMuseumsService.findByPlaceMuseumsResponse(request);
    }


}
