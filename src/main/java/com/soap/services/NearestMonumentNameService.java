package com.soap.services;

import com.soap.dao.MonumentDao;
import com.soap.jpa.DbMonument;
import com.soap.model.GetNearestNameRequest;
import com.soap.model.GetNearestNameResponse;
import com.soap.model.Monument;
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
import java.util.Map;

@Service
public class MonumentNameService {


    private MonumentDao monumentDao;
    private Logger logger = LoggerFactory.getLogger(MonumentNameService.class);

    @Autowired
    public MonumentNameService(MonumentDao monumentDao) {
        this.monumentDao = monumentDao;
    }


    //return a response with the nearest name based on distance between the input(lat&long) and the points in db
    public GetNearestNameResponse getNearestNameResponse(@RequestPayload GetNearestNameRequest request) {
        GetNearestNameResponse response = new GetNearestNameResponse();


            Point myPoint = GeometryHelper.createPoint(request.getLat(), request.getLong());
            Map<DbMonument, Double> monumentDistanceOpMap = new HashMap<>();

            for (DbMonument dbmonument : monumentDao.getAllDbMonuments()) {

                monumentDistanceOpMap.put(dbmonument, new DistanceOp(myPoint, dbmonument.getPoint()).distance());
            }
            monumentDistanceOpMap.forEach((dbMonument, aDouble) -> logger.info("Distance from my point and " + dbMonument.getName() + " is " + aDouble));
            DbMonument dbMonument = MonumentUtil.retrieveMinDistanceMonument(monumentDistanceOpMap);
            Monument monument = MonumentUtil.fromdbToMonument(dbMonument);


            MonumentUtil.update(monument);

            MonumentUtil.getMonuments().add(monument);

            response.setMessage(Messages.NEAREST_FOUND.info);
            response.setMonumentName(dbMonument.getName());

//        } else {
//
//            response.setMessage(Messages.INVALID_COORDINATES.info);
//
//
//        }
//        return response;

        return response;

    }


}

