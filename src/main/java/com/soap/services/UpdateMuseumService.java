package com.soap.services;

import com.soap.dao.MuseumDao;
import com.soap.jpa.DbMuseum;
import com.soap.model.UpdateMuseumRequest;
import com.soap.model.UpdateMuseumResponse;
import com.soap.utilities.GeometryHelper;
import com.soap.utilities.Messages;
import com.soap.utilities.MuseumUtil;
import org.locationtech.jts.geom.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

/**
 * @author Grigorios Ladas
 */
@Service
public class UpdateMuseumService {


    private final MuseumDao museumDao;
    private final Logger logger = LoggerFactory.getLogger(UpdateMuseumService.class);

    @Autowired
    public UpdateMuseumService(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }

    public UpdateMuseumResponse UpdateMuseumResponse(@RequestPayload UpdateMuseumRequest request) {
        UpdateMuseumResponse response = new UpdateMuseumResponse();
        MuseumUtil museumUtil = MuseumUtil.getInstance();
        if (museumUtil.isValidCoordinates(request.getLatitude(), request.getLongitude())) {
            String museumName = request.getName().toLowerCase();
            DbMuseum dbMuseum = museumDao.findMuseum(museumName);
            if (dbMuseum != null) {
                logger.info("Museum " + dbMuseum.getName() + " has coordinates " + dbMuseum.getPoint() + " before update");
                Point point = GeometryHelper.createPoint(request.getLatitude(), request.getLongitude());
                dbMuseum.setPoint(point);
                dbMuseum.setDescription(request.getDescription());
                museumDao.updateMuseum(dbMuseum);
                response.setMessage(Messages.MUSEUM_UPDATED.info);
                logger.info("Museum " + dbMuseum.getName() + " has coordinates " + dbMuseum.getPoint() + " after update");
            } else {
                response.setMessage(Messages.MUSEUM_REJECTED.info);
                logger.info("Museum " + museumName + " was not added in database");
            }
        } else {
            logger.info("Check your coordinates");
            response.setMessage(Messages.MUSEUM_NOT_UPDATED.info);
        }
        return response;
    }
}
