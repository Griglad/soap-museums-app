package com.soap.controllers;

import com.soap.model.UpdateMuseumRequest;
import com.soap.model.UpdateMuseumResponse;
import com.soap.services.UpdateMuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class UpdateMuseumController {


    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final UpdateMuseumService updateMuseumService;


    @Autowired
    public UpdateMuseumController(UpdateMuseumService updateMuseumService) {
        this.updateMuseumService = updateMuseumService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateMuseumRequest")
    @ResponsePayload
    public UpdateMuseumResponse updateMuseumResponse(@RequestPayload UpdateMuseumRequest request) {

        return updateMuseumService.UpdateMuseumResponse(request);

    }


}
