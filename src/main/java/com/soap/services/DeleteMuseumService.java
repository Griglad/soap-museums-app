package com.soap.services;

import com.soap.dao.MuseumDao;
import com.soap.jpa.DbMuseum;
import com.soap.model.DeleteMuseumRequest;
import com.soap.model.DeleteMuseumResponse;
import com.soap.utilities.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

/**
 * @author Grigorios Ladas
 */
@Service
public class DeleteMuseumService {

    private final MuseumDao museumDao;
    private final Logger logger = LoggerFactory.getLogger(DeleteMuseumService.class);


    @Autowired
    public DeleteMuseumService(MuseumDao museumDao) {

        this.museumDao = museumDao;

    }

    public DeleteMuseumResponse deleteMuseumResponse(@RequestPayload DeleteMuseumRequest request) {
        DeleteMuseumResponse response = new DeleteMuseumResponse();
        String museumName = request.getName().toLowerCase();
        DbMuseum dbMuseum = museumDao.findMuseum(museumName);
        if (dbMuseum != null) {
            museumDao.deleteMuseum(dbMuseum);
            response.setMessage(Messages.MUSEUM_DELETED.info);
            logger.info( museumName + " was deleted from database");
        } else {
            response.setMessage(Messages.MUSEUM_NOT_FOUND.info);
            logger.info(museumName + " was not found in database");
        }

        return response;
    }


}
