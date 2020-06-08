package com.soap.services;

import com.soap.dao.MonumentDao;
import com.soap.jpa.DbMonument;
import com.soap.model.UpdateMonumentRequest;
import com.soap.model.UpdateMonumentResponse;
import com.soap.utilities.GeometryHelper;
import com.soap.utilities.Messages;
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
public class UpdateMonumentService {


    private final MonumentDao monumentDao;

    private Logger logger = LoggerFactory.getLogger(NearestMonumentNameService.class);

    @Autowired
    public UpdateMonumentService(MonumentDao monumentDao) {
        this.monumentDao = monumentDao;
    }

    public UpdateMonumentResponse updateMonumentResponse(@RequestPayload UpdateMonumentRequest request) {
        UpdateMonumentResponse response = new UpdateMonumentResponse();
        if (request.getLatitude() != 0 && request.getLongitude() != 0) {
            String monumentName = request.getName().toLowerCase();
            DbMonument dbMonument = monumentDao.findMonument(monumentName);
            if (dbMonument != null) {
                logger.info("Monument " + dbMonument.getName() + " has coordinates " + dbMonument.getPoint() + " before update");
                Point point = GeometryHelper.createPoint(request.getLatitude(), request.getLongitude());
                dbMonument.setPoint(point);
                dbMonument.setDescription(request.getDescription());
                monumentDao.updateMonument(dbMonument);
                response.setMessage(Messages.MONUMENT_UPDATED.info);
                logger.info("Monument " + dbMonument.getName() + " has coordinates " + dbMonument.getPoint() + " after update");
            } else {
                response.setMessage(Messages.MONUMENT_REJECTED.info);
            }
        } else {
            response.setMessage(Messages.MONUMENT_NOT_UPDATED.info);
        }
        return response;
    }
}
