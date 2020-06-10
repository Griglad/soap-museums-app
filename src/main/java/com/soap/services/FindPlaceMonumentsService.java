package com.soap.services;

import com.soap.dao.MonumentDao;
import com.soap.jpa.DbMonument;
import com.soap.model.FindPlaceMonumentsRequest;
import com.soap.model.FindPlaceMonumentsResponse;
import com.soap.model.Monument;
import com.soap.utilities.Messages;
import com.soap.utilities.MonumentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author Grigorios Ladas
 */
@Service
public class FindPlaceMonumentsService {


    private final MonumentDao monumentDao;


    @Autowired
    public FindPlaceMonumentsService(MonumentDao monumenDao) {

        this.monumentDao = monumenDao;

    }


    public FindPlaceMonumentsResponse findPlaceMonumentsResponse(@RequestPayload FindPlaceMonumentsRequest request) {
        FindPlaceMonumentsResponse response = new FindPlaceMonumentsResponse();
        String place= request.getPlace().toLowerCase().trim();
        List<DbMonument> dbMonuments = monumentDao.findMonumentsByTown(place);
        if (!dbMonuments.isEmpty()) {
            List<Monument> monuments = dbMonuments.stream().map(MonumentUtil::fromdbToMonument).collect(Collectors.toList());
            response.setMessage(place.toUpperCase() + Messages.MONUMENTS_FOUND.info);
            response.getPlaceMonuments().addAll(monuments);

        } else {
            response.setMessage(place.toUpperCase() + Messages.MONUMENTS_NOT_FOUND.info);
        }

        return response;


    }


}
