package com.soap.controllers;

import com.soap.model.*;
import com.soap.services.MonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MonumentNameController {

    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private MonumentService monumentService;

    @Autowired
    public MonumentNameController(MonumentService monumentService) {

        this.monumentService = monumentService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMonumentNameRequest")
    @ResponsePayload
    public GetMonumentNameResponse getMonumentName(@RequestPayload GetMonumentNameRequest request) {


        return monumentService.getMonumentNameResponse(request);
    }


}