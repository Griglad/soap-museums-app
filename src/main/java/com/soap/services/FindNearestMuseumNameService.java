package com.soap.services;

import com.soap.dao.MuseumDao;
import com.soap.jpa.DbMuseum;
import com.soap.model.FindNearestNameRequest;
import com.soap.model.FindNearestNameResponse;
import com.soap.utilities.GeometryHelper;
import com.soap.utilities.Messages;
import com.soap.utilities.MuseumUtil;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.*;

/**
 * @author Grigorios Ladas
 */

@Service
public class FindNearestMuseumNameService {


    private final MuseumDao museumDao;
    private final Map<DbMuseum, Double> museumDistanceOnMap = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(FindNearestMuseumNameService.class);
    private static final Set<DbMuseum> dbMuseumSet = new LinkedHashSet<>();

    @Autowired
    public FindNearestMuseumNameService(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }


    //Return a response with the nearest museums name based on distance between the input(lat&long) and the museums coordinates in db
    public FindNearestNameResponse FindNearestNameResponse(@RequestPayload FindNearestNameRequest request) {
        FindNearestNameResponse response = new FindNearestNameResponse();
        MuseumUtil util = MuseumUtil.getInstance();
        if (util.isValidCoordinates(request.getLatitude(), request.getLongitude())) {
            Point myPoint = GeometryHelper.createPoint(request.getLatitude(), request.getLongitude());
            List<DbMuseum> dbMuseums = museumDao.getAllDbMuseums();
            if (dbMuseums != null) {
                for (DbMuseum dbmuseum : dbMuseums) {

                    museumDistanceOnMap.put(dbmuseum, new DistanceOp(myPoint, dbmuseum.getPoint()).distance());
                }
                museumDistanceOnMap.forEach((dbMuseum, aDouble) -> logger.info("Distance from my point and museum " + dbMuseum.getName() + " is " + aDouble + " units"));
                DbMuseum dbMuseum = util.retrieveMinDistanceMuseum(museumDistanceOnMap);
                util.updateCounter(dbMuseum);
                museumDao.updateMuseum(dbMuseum);
                dbMuseumSet.add(dbMuseum);
                response.setMessage(Messages.NEAREST_FOUND.info);
                response.setMuseumName(dbMuseum.getName());
                response.setDescription(dbMuseum.getDescription());
            } else {
                response.setMessage(Messages.CHECK_DATABASE.info);
            }

        } else {
            response.setMessage(Messages.CHECK_LAT_LONG.info);

        }
        return response;

    }


    public static Set<DbMuseum> getDbMuseumSet() {
        return dbMuseumSet;
    }

}


