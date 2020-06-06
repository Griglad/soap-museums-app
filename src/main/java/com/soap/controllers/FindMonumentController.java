package com.soap.controllers;

import com.soap.model.FindMonumentRequest;
import com.soap.model.FindMonumentResponse;
import com.soap.services.FindMonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class FindMonumentController {

    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final FindMonumentService findMonumentService;

    @Autowired
    public FindMonumentController(FindMonumentService findMonumentService) {
        this.findMonumentService = findMonumentService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findMonumentRequest")
    @ResponsePayload
    public FindMonumentResponse findMonumentResponse(@RequestPayload FindMonumentRequest request) {
        return findMonumentService.findMonumentResponse(request);
    }
}
