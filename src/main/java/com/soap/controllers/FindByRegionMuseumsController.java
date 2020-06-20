package com.soap.controllers;

import com.soap.model.FindByRegionMuseumsRequest;
import com.soap.model.FindByRegionMuseumsResponse;
import com.soap.services.FindByRegionMuseumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class FindByRegionMuseumsController {


    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final FindByRegionMuseumsService findByRegionMuseumsService;


    @Autowired
    public FindByRegionMuseumsController(FindByRegionMuseumsService findByRegionMuseumsService) {
        this.findByRegionMuseumsService = findByRegionMuseumsService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findByRegionMuseumsRequest")
    @ResponsePayload
    public FindByRegionMuseumsResponse findByRegionMuseumsResponse(@RequestPayload FindByRegionMuseumsRequest request) {
        return findByRegionMuseumsService.findByRegionMuseumsResponse(request);
    }


}
