package com.soap.services;

import com.soap.dao.MonumentDao;
import com.soap.jpa.DbMonument;
import com.soap.model.DeleteMonumentRequest;
import com.soap.model.DeleteMonumentResponse;
import com.soap.utilities.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

/**
 * @author Grigorios Ladas
 */
@Service
public class DeleteMonumentService {

    private final MonumentDao monumentDao;

    @Autowired
    public DeleteMonumentService(MonumentDao monumenDao) {

        this.monumentDao = monumenDao;

    }

    public DeleteMonumentResponse deleteMonumentResponse(@RequestPayload DeleteMonumentRequest request) {
        DeleteMonumentResponse deleteResponse = new DeleteMonumentResponse();
        String monumentName = request.getName().toLowerCase();
        DbMonument dbMonument = monumentDao.findMonument(monumentName);

        if (dbMonument != null) {
            monumentDao.deleteMonument(dbMonument);
            deleteResponse.setMessage(Messages.MONUMENT_DELETED.info);
        } else {
            deleteResponse.setMessage(Messages.MONUMENT_NOT_FOUND.info);
        }

        return deleteResponse;
    }


}
