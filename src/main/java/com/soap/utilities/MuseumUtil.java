package com.soap.utilities;

import com.soap.jpa.DbMuseum;
import com.soap.model.Museum;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Grigorios Ladas
 */
public class MuseumUtil {

    private static final double startingLatitude = 35.01186;
    private static final double endingLongitude = 28.2225;

    private static Set<DbMuseum> dbMuseums = new LinkedHashSet<>();


    public static Set<DbMuseum> getDbMuseums() {

        return dbMuseums;
    }


    public static Museum fromdbToMuseum(DbMuseum dbMuseum) {
        Museum museum = new Museum();
        museum.setName(dbMuseum.getName());
        museum.setLatitude(dbMuseum.getPoint().getX());
        museum.setLongitude(dbMuseum.getPoint().getY());
        museum.setPlace(dbMuseum.getPlace());
        museum.setRegion(dbMuseum.getRegion());
        museum.setDescription(dbMuseum.getDescription());
        return museum;
    }

    public static DbMuseum fromMuseumToDb(Museum museum) {
        DbMuseum dbmuseum = new DbMuseum();
        dbmuseum.setName(museum.getName());
        dbmuseum.setRegion(museum.getRegion());
        dbmuseum.setPlace(museum.getPlace());
        dbmuseum.setDescription(museum.getDescription());
        dbmuseum.setPoint(GeometryHelper.createPoint(museum.getLatitude(), museum.getLongitude()));
        return dbmuseum;
    }

    public static DbMuseum retrieveMinDistanceMuseum(Map<DbMuseum, Double> museumDoubleMap) {

        DbMuseum museum = Collections.min(museumDoubleMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        return museum;

    }


    public static void updateCounter(DbMuseum dbMuseum) {
        long counter = dbMuseum.getCounter();
        dbMuseum.setCounter(++counter);
    }

    public static double getStartingLatitude() {
        return startingLatitude;
    }

    public static double getEndingLongitude() {
        return endingLongitude;
    }


}