package com.soap.services;

import com.soap.dao.MuseumDao;
import com.soap.jpa.DbMuseum;
import com.soap.model.FindMuseumRequest;
import com.soap.model.FindMuseumResponse;
import com.soap.model.Museum;
import com.soap.utilities.Messages;
import com.soap.utilities.MuseumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

/**
 * @author Grigorios Ladas
 */
@Service
public class FindMuseumService {


    private final MuseumDao MuseumDao;

    @Autowired
    public FindMuseumService(MuseumDao MuseumDao) {
        this.MuseumDao = MuseumDao;
    }

    public FindMuseumResponse findMuseumResponse(@RequestPayload FindMuseumRequest request) {

        FindMuseumResponse response = new FindMuseumResponse();
        String MuseumName = request.getName().toLowerCase().trim();

        DbMuseum dbMuseum = MuseumDao.findMuseum(MuseumName);

        if (dbMuseum != null) {
            response.setMessage(Messages.MUSEUM_FOUND.info);
            Museum Museum = MuseumUtil.fromdbToMuseum(dbMuseum);
            response.setMuseum(Museum);
            return response;
        } else {
            response.setMessage(Messages.MUSEUM_NOT_FOUND.info);

            return response;
        }


    }
}
