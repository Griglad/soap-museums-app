package com.soap.controllers;

import com.soap.model.GetNearestNameRequest;
import com.soap.model.GetNearestNameResponse;
import com.soap.services.MonumentNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MonumentNameController {

    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private MonumentNameService monumentService;

    @Autowired
    public MonumentNameController(MonumentNameService monumentService) {

        this.monumentService = monumentService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNearestNameRequest")
    @ResponsePayload
    public GetNearestNameResponse getNearestNameResponse(@RequestPayload GetNearestNameRequest request) {


        return monumentService.getNearestNameResponse(request);
    }


}