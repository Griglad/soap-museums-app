package com.soap.controllers;

import com.soap.model.GetNearestNameRequest;
import com.soap.model.GetNearestNameResponse;
import com.soap.services.NearestMonumentNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class NearestMonumentNameController {

    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final NearestMonumentNameService monumentService;

    @Autowired
    public NearestMonumentNameController(NearestMonumentNameService monumentService) {

        this.monumentService = monumentService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNearestNameRequest")
    @ResponsePayload
    public GetNearestNameResponse getNearestNameResponse(@RequestPayload GetNearestNameRequest request) {


        return monumentService.getNearestNameResponse(request);
    }


}