package com.soap.services;

import com.soap.dao.MuseumDao;
import com.soap.jpa.DbMuseum;
import com.soap.model.DeleteMuseumRequest;
import com.soap.model.DeleteMuseumResponse;
import com.soap.utilities.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

/**
 * @author Grigorios Ladas
 */
@Service
public class DeleteMuseumService {

    private final MuseumDao museumDao;

    @Autowired
    public DeleteMuseumService(MuseumDao monumenDao) {

        this.museumDao = monumenDao;

    }

    public DeleteMuseumResponse deleteMuseumResponse(@RequestPayload DeleteMuseumRequest request) {
        DeleteMuseumResponse response = new DeleteMuseumResponse();
        String museumName = request.getName().toLowerCase();
        DbMuseum dbMuseum = museumDao.findMuseum(museumName);

        if (dbMuseum != null) {
            museumDao.deleteMuseum(dbMuseum);
            response.setMessage(Messages.MUSEUM_DELETED.info);
        } else {
            response.setMessage(Messages.MUSEUM_NOT_FOUND.info);
        }

        return response;
    }


}
