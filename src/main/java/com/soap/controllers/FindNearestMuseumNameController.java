package com.soap.controllers;

import com.soap.model.FindNearestNameRequest;
import com.soap.model.FindNearestNameResponse;
import com.soap.services.FindNearestMuseumNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class FindNearestMuseumNameController {

    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final FindNearestMuseumNameService findNearestMuseumNameService;

    @Autowired
    public FindNearestMuseumNameController(FindNearestMuseumNameService findNearestMuseumNameService) {

        this.findNearestMuseumNameService = findNearestMuseumNameService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findNearestNameRequest")
    @ResponsePayload
    public FindNearestNameResponse findNearestNameResponse(@RequestPayload FindNearestNameRequest request) {


        return findNearestMuseumNameService.FindNearestNameResponse(request);
    }


}