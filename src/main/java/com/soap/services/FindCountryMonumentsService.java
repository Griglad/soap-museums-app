package com.soap.services;

import com.soap.dao.MonumentDao;
import com.soap.jpa.DbMonument;
import com.soap.model.FindCountryMonumentsRequest;
import com.soap.model.FindCountryMonumentsResponse;
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
public class FindCountryMonumentsService {

    private final MonumentDao monumentDao;


    @Autowired
    public FindCountryMonumentsService(MonumentDao monumenDao) {

        this.monumentDao = monumenDao;

    }

    public FindCountryMonumentsResponse findCountryMonumentsResponse(@RequestPayload FindCountryMonumentsRequest request) {

        FindCountryMonumentsResponse response = new FindCountryMonumentsResponse();
        String country = request.getCountry().toLowerCase().trim();
        List<DbMonument> dbMonuments = monumentDao.findMonumentsByCountry(country);
        if (!dbMonuments.isEmpty()) {
            List<Monument> monuments = dbMonuments.stream().map(MonumentUtil::fromdbToMonument).collect(Collectors.toList());
            response.setMessage(Messages.MONUMENTS_FOUND.info);
            response.getCountryMonuments().addAll(monuments);

        } else {
            response.setMessage(Messages.MONUMENTS_NOT_FOUND.info);
        }

        return response;

    }


}
