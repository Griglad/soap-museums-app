package com.soap.services;

import com.soap.dao.MonumentDao;
import com.soap.jpa.DbMonument;
import com.soap.model.InputMonumentRequest;
import com.soap.model.InputMonumentResponse;
import com.soap.model.Monument;
import com.soap.utilities.Messages;
import com.soap.utilities.MonumentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

/**
 * @author Grigorios Ladas
 */
@Service
public class InputMonumentService {


    private final MonumentDao monumentDao;
    private static final double  startingLatitude = 35.01186;
    private static final double  endingLongitude = 28.2225;



    private Logger logger = LoggerFactory.getLogger(InputMonumentService.class);

    @Autowired
    public InputMonumentService(MonumentDao monumentDao) {
        this.monumentDao = monumentDao;
    }


    public InputMonumentResponse inputMonumentResponse(@RequestPayload InputMonumentRequest request) {
        final String regex = "^\\D{4,35}+(\\s\\D{4,35}+)*$";
        InputMonumentResponse response = new InputMonumentResponse();
        Monument monument = new Monument();
        String monumentName = request.getName().toLowerCase();
        if (monumentDao.findMonument(monumentName) != null) {
            response.setMessage(Messages.MONUMENT_EXIST.info);
        } else if (request.getName().matches(regex) && request.getRegion().matches(regex) &&
                request.getLatitude() >= startingLatitude && request.getLongitude() <= endingLongitude) {
            String name = request.getName();
            String regionName = request.getRegion();
            monument.setName(name.toUpperCase().trim());
            monument.setRegion(regionName.toUpperCase().trim());
            monument.setLatitude(request.getLatitude());
            monument.setLongitude(request.getLongitude());
            DbMonument dbMonument = MonumentUtil.fromMonumentToDb(monument);
            logger.info(dbMonument.getName() + " with " + dbMonument.getPoint() + " is going to be added to the database");
            monumentDao.addDbMonument(dbMonument);
            response.setMessage(Messages.MONUMENT_ADDED.info);
        } else {
            response.setMessage(Messages.MONUMENT_REJECTED.info);
        }

        return response;

    }


}
