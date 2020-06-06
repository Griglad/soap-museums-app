package com.soap.controllers;

import com.soap.model.InputMonumentRequest;
import com.soap.model.InputMonumentResponse;
import com.soap.services.InputMonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class InputMonumentController {


    private static final String NAMESPACE_URI = "http://www.soap.com/model";

    private final InputMonumentService monumentService;


    @Autowired
    public InputMonumentController(InputMonumentService monumentService) {
        this.monumentService = monumentService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "inputMonumentRequest")
    @ResponsePayload
    public InputMonumentResponse inputMonumentResponse(@RequestPayload InputMonumentRequest request) {
        return monumentService.inputMonumentResponse(request);
    }
}
