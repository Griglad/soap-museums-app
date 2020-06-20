package com.soap.controllers;

import com.soap.model.InputMuseumRequest;
import com.soap.model.InputMuseumResponse;
import com.soap.services.InputMuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class InputMuseumController {


    private static final String NAMESPACE_URI = "http://www.soap.com/model";

    private final InputMuseumService inputMuseumService;


    @Autowired
    public InputMuseumController(InputMuseumService inputMuseumService) {
        this.inputMuseumService = inputMuseumService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "inputMuseumRequest")
    @ResponsePayload
    public InputMuseumResponse inputMuseumResponse(@RequestPayload InputMuseumRequest request) {
        return inputMuseumService.inputMuseumResponse(request);
    }
}
