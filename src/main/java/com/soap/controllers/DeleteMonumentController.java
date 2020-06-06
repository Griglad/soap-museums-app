package com.soap.controllers;

import com.soap.model.DeleteMonumentRequest;
import com.soap.model.DeleteMonumentResponse;
import com.soap.services.DeleteMonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Grigorios Ladas
 */
@Endpoint
public class DeleteMonumentController {


    private static final String NAMESPACE_URI = "http://www.soap.com/model";
    private final DeleteMonumentService deleteMonumentService;


    @Autowired
    public DeleteMonumentController(DeleteMonumentService deleteMonumentService) {
        this.deleteMonumentService = deleteMonumentService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMonumentRequest")
    @ResponsePayload
    public DeleteMonumentResponse deleteMonumentResponse(@RequestPayload DeleteMonumentRequest request) {
        return deleteMonumentService.deleteMonumentResponse(request);
    }


}
