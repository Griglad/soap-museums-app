package com.soap.services;

import com.soap.dao.MuseumDao;
import com.soap.jpa.DbMuseum;
import com.soap.model.FindByRegionMuseumsRequest;
import com.soap.model.FindByRegionMuseumsResponse;
import com.soap.model.Museum;
import com.soap.utilities.Messages;
import com.soap.utilities.MuseumUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(FindByRegionMuseumsService.class);


    @Autowired
    public FindByRegionMuseumsService(MuseumDao museumDao) {

        this.museumDao = museumDao;

    }

    public FindByRegionMuseumsResponse findByRegionMuseumsResponse(@RequestPayload FindByRegionMuseumsRequest request) {
        FindByRegionMuseumsResponse response = new FindByRegionMuseumsResponse();
        MuseumUtil museumUtil = MuseumUtil.createInstance();
        String region = request.getRegion().toLowerCase().trim();
        List<DbMuseum> dbMuseums = museumDao.findMuseumsByRegion(region);
        if (!dbMuseums.isEmpty()) {
            logger.info("Museums by regions found " + dbMuseums);
            List<Museum> museums = dbMuseums.stream().map(museumUtil::fromdbToMuseum).collect(Collectors.toList());
            response.setMessage(region.toUpperCase() + Messages.MUSEUMS_FOUND.info);
            response.getMuseumsByRegion().addAll(museums);

        } else {
            logger.info("Museums from region " + region + " were not found");
            response.setMessage(region.toUpperCase() + Messages.MUSEUMS_NOT_FOUND.info);
        }
        return response;
    }


}
