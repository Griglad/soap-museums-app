package com.soap.controllers;

import com.soap.model.UpdateMonumentRequest;
import com.soap.model.UpdateMonumentResponse;
import com.soap.services.UpdateMonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class UpdateMonumentController {

    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final UpdateMonumentService updateMonumentService;


    @Autowired
    public UpdateMonumentController(UpdateMonumentService updateMonumentService) {
        this.updateMonumentService = updateMonumentService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateMonumentRequest")
    @ResponsePayload
    public UpdateMonumentResponse updateMonumentResponse(@RequestPayload UpdateMonumentRequest request) {

        return updateMonumentService.updateMonumentResponse(request);

    }


}
