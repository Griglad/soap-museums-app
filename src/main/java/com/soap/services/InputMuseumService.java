package com.soap.services;

import com.soap.dao.MuseumDao;
import com.soap.jpa.DbMuseum;
import com.soap.model.InputMuseumRequest;
import com.soap.model.InputMuseumResponse;
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
public class InputMuseumService {


    private final MuseumDao museumDao;
    private final Logger logger = LoggerFactory.getLogger(InputMuseumService.class);

    @Autowired
    public InputMuseumService(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }


    public InputMuseumResponse inputMuseumResponse(@RequestPayload InputMuseumRequest request) {
        InputMuseumResponse response = new InputMuseumResponse();
        Museum Museum = new Museum();
        String museumName = request.getName().toLowerCase();
        if (museumDao.findMuseum(museumName) != null) {
            logger.info(museumName + " already exists in database");
            response.setMessage(Messages.MUSEUM_EXIST.info);
        } else if (MuseumUtil.isValidPattern(request.getName(), request.getRegion(), request.getPlace()) &&
                   MuseumUtil.isValidCoordinates(request.getLatitude(),request.getLongitude())) {
            String name = request.getName();
            String regionName = request.getRegion();
            String townName = request.getPlace();
            String description = request.getDescription();
            Museum.setName(name.toUpperCase().trim());
            Museum.setPlace(townName.toUpperCase().trim());
            Museum.setRegion(regionName.toUpperCase().trim());
            Museum.setDescription(description);
            Museum.setLatitude(request.getLatitude());
            Museum.setLongitude(request.getLongitude());
            DbMuseum dbMuseum = MuseumUtil.fromMuseumToDb(Museum);
            museumDao.addDbMuseum(dbMuseum);
            logger.info(dbMuseum.getName() + " with " + dbMuseum.getPoint() + " was added into the database");
            response.setMessage(Messages.MUSEUM_ADDED.info);
        } else {
            logger.info(museumName + " was not added in database please check your elements ");
            response.setMessage(Messages.MUSEUM_REJECTED.info);
        }

        return response;

    }


}
