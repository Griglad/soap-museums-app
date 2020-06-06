package com.soap.services;

import com.soap.dao.MonumentDao;
import com.soap.jpa.DbMonument;
import com.soap.model.GetNearestNameRequest;
import com.soap.model.GetNearestNameResponse;
import com.soap.utilities.GeometryHelper;
import com.soap.utilities.Messages;
import com.soap.utilities.MonumentUtil;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Grigorios Ladas
 */

@Service
public class NearestMonumentNameService {


    private final MonumentDao monumentDao;
    private static final Map<DbMonument, Double> monumentDistanceOnMap = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(NearestMonumentNameService.class);

    @Autowired
    public NearestMonumentNameService(MonumentDao monumentDao) {
        this.monumentDao = monumentDao;
    }


    //Return a response with the nearest monument name based on distance between the input(lat&long) and the monuments coordinates in db
    public GetNearestNameResponse getNearestNameResponse(@RequestPayload GetNearestNameRequest request) {
        GetNearestNameResponse response = new GetNearestNameResponse();
        if (request.getLatitude() != 0 && request.getLongitude() != 0) {
            Point myPoint = GeometryHelper.createPoint(request.getLatitude(), request.getLongitude());
            List<DbMonument> dbMonuments = monumentDao.getAllDbMonuments();
            if (dbMonuments != null) {
                for (DbMonument dbmonument : dbMonuments) {

                    monumentDistanceOnMap.put(dbmonument, new DistanceOp(myPoint, dbmonument.getPoint()).distance());
                }
                monumentDistanceOnMap.forEach((dbMonument, aDouble) -> logger.info("Distance from my point and monument " + dbMonument.getName() + " is " + aDouble + " units"));
                DbMonument dbMonument = MonumentUtil.retrieveMinDistanceMonument(monumentDistanceOnMap);
                MonumentUtil.updateCounter(dbMonument);
                MonumentUtil.getMonuments().add(dbMonument);
                response.setMessage(Messages.NEAREST_FOUND.info);
                response.setMonumentName(dbMonument.getName());
            } else {
                response.setMessage(Messages.CHECK_DATABASE.info);
            }

        } else {
            response.setMessage(Messages.CHECK_LAT_LONG.info);

        }
        return response;

    }


}


