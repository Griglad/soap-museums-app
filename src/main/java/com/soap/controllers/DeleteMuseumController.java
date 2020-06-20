package com.soap.controllers;

import com.soap.model.DeleteMuseumRequest;
import com.soap.model.DeleteMuseumResponse;
import com.soap.services.DeleteMuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class DeleteMuseumController {


    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final DeleteMuseumService deleteMuseumService;


    @Autowired
    public DeleteMuseumController(DeleteMuseumService deleteMuseumService) {
        this.deleteMuseumService = deleteMuseumService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMuseumRequest")
    @ResponsePayload
    public DeleteMuseumResponse DeleteMuseumResponse(@RequestPayload DeleteMuseumRequest request) {
        return deleteMuseumService.deleteMuseumResponse(request);
    }


}
