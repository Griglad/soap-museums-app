package com.soap.services;

import com.soap.dao.MonumentDao;
import com.soap.jpa.DbMonument;
import com.soap.model.FindRegionMonumentsRequest;
import com.soap.model.FindRegionMonumentsResponse;
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
public class FindRegionMonumentsService {

    private final MonumentDao monumentDao;


    @Autowired
    public FindRegionMonumentsService(MonumentDao monumenDao) {

        this.monumentDao = monumenDao;

    }

    public FindRegionMonumentsResponse findCountryMonumentsResponse(@RequestPayload FindRegionMonumentsRequest request) {

        FindRegionMonumentsResponse response = new FindRegionMonumentsResponse();
        String region = request.getRegion().toLowerCase().trim();
        List<DbMonument> dbMonuments = monumentDao.findMonumentsByRegion(region);
        if (!dbMonuments.isEmpty()) {
            List<Monument> monuments = dbMonuments.stream().map(MonumentUtil::fromdbToMonument).collect(Collectors.toList());
            response.setMessage(region.toUpperCase() + Messages.MONUMENTS_FOUND.info);
            response.getRegionMonuments().addAll(monuments);

        } else {
            response.setMessage(Messages.MONUMENTS_NOT_FOUND.info);
        }

        return response;

    }


}
