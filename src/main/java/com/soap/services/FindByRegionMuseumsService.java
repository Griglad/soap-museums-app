package com.soap.services;

import com.soap.dao.MuseumDao;
import com.soap.jpa.DbMuseum;
import com.soap.model.FindByRegionMuseumsRequest;
import com.soap.model.FindByRegionMuseumsResponse;
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
public class FindByRegionMuseumsService {

    private final MuseumDao museumDao;


    @Autowired
    public FindByRegionMuseumsService(MuseumDao monumenDao) {

        this.museumDao = monumenDao;

    }

    public FindByRegionMuseumsResponse findByRegionMuseumsResponse(@RequestPayload FindByRegionMuseumsRequest request) {

        FindByRegionMuseumsResponse response = new FindByRegionMuseumsResponse();
        String region = request.getRegion().toLowerCase().trim();
        List<DbMuseum> dbMuseums = museumDao.findMuseumsByRegion(region);
        if (!dbMuseums.isEmpty()) {
            List<Museum> museums = dbMuseums.stream().map(MuseumUtil::fromdbToMuseum).collect(Collectors.toList());
            response.setMessage(region.toUpperCase() + Messages.MUSEUMS_FOUND.info);
            response.getMuseumsByRegion().addAll(museums);

        } else {
            response.setMessage(region.toUpperCase() + Messages.MUSEUMS_NOT_FOUND.info);
        }

        return response;

    }


}
