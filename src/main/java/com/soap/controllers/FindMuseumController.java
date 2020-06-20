package com.soap.controllers;

import com.soap.model.FindMuseumRequest;
import com.soap.model.FindMuseumResponse;
import com.soap.services.FindMuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class FindMuseumController {

    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final FindMuseumService findMuseumService;

    @Autowired
    public FindMuseumController(FindMuseumService findMuseumService) {
        this.findMuseumService = findMuseumService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findMuseumRequest")
    @ResponsePayload
    public FindMuseumResponse FindMuseumResponse(@RequestPayload FindMuseumRequest request) {
        return findMuseumService.findMuseumResponse(request);
    }
}
