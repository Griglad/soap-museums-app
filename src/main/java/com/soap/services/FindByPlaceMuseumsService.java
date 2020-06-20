package com.soap.services;

import com.soap.dao.MuseumDao;
import com.soap.jpa.DbMuseum;
import com.soap.model.FindByPlaceMuseumsRequest;
import com.soap.model.FindByPlaceMuseumsResponse;
import com.soap.model.Museum;
import com.soap.utilities.Messages;
import com.soap.utilities.MuseumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author Grigorios Ladas
 */
@Service
public class FindByPlaceMuseumsService {


    private final MuseumDao museumDao;


    @Autowired
    public FindByPlaceMuseumsService(MuseumDao monumenDao) {

        this.museumDao = monumenDao;

    }


    public FindByPlaceMuseumsResponse findByPlaceMuseumsResponse(@RequestPayload FindByPlaceMuseumsRequest request) {
        FindByPlaceMuseumsResponse response = new FindByPlaceMuseumsResponse();
        String place= request.getPlace().toLowerCase().trim();
        List<DbMuseum> dbMuseums = museumDao.findMuseumsByPlace(place);
        if (!dbMuseums.isEmpty()) {
            List<Museum> Museums = dbMuseums.stream().map(MuseumUtil::fromdbToMuseum).collect(Collectors.toList());
            response.setMessage(place.toUpperCase() + Messages.MUSEUMS_FOUND.info);
            response.getMuseumsByPlace().addAll(Museums);

        } else {
            response.setMessage(place.toUpperCase() + Messages.MUSEUMS_NOT_FOUND.info);
        }

        return response;


    }


}
