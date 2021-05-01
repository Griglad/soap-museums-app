package com.soap.services;

import com.soap.dao.MuseumDao;
import com.soap.jpa.DbMuseum;
import com.soap.model.FindMuseumRequest;
import com.soap.model.FindMuseumResponse;
import com.soap.model.Museum;
import com.soap.utilities.Messages;
import com.soap.utilities.MuseumUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

/**
 * @author Grigorios Ladas
 */
@Service
public class FindMuseumService {

    private final MuseumDao MuseumDao;
    private final Logger logger = LoggerFactory.getLogger(FindMuseumService.class);


    @Autowired
    public FindMuseumService(MuseumDao MuseumDao) {
        this.MuseumDao = MuseumDao;
    }

    public FindMuseumResponse findMuseumResponse(@RequestPayload FindMuseumRequest request) {
        FindMuseumResponse response = new FindMuseumResponse();
        MuseumUtil museumUtil = MuseumUtil.createInstance();
        String museumName = request.getName().toLowerCase().trim();
        DbMuseum dbMuseum = MuseumDao.findMuseum(museumName);
        if (dbMuseum != null) {
            logger.info("Museum which was found is " + museumName);
            response.setMessage(Messages.MUSEUM_FOUND.info);
            Museum museum = museumUtil.fromdbToMuseum(dbMuseum);
            response.setMuseum(museum);
            return response;
        } else {
            logger.info(museumName + " was not found");
            response.setMessage(Messages.MUSEUM_NOT_FOUND.info);
            return response;
        }
    }
}
