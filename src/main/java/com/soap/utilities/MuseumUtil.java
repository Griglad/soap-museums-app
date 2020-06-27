package com.soap.utilities;

import com.soap.jpa.DbMuseum;
import com.soap.model.Museum;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Grigorios Ladas
 */
public final class MuseumUtil {

    private static final double startingLatitude = 35.01186;
    private static final double endingLongitude = 28.2225;
    private static final Pattern inputPattern = Pattern.compile("^\\D{4,80}+(\\s\\D{4,80}+)*$");
    private static final Set<DbMuseum> dbMuseums = new LinkedHashSet<>();



    private MuseumUtil(){

    }

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


    public static boolean isValidPattern(String... params) {

        return Arrays.stream(params).allMatch(e -> inputPattern.matcher(e).matches());
    }

    public static boolean isValidCoordinates(double latitude,double longitude){
        return Double.compare(latitude,startingLatitude)>=0 && Double.compare(longitude,endingLongitude)<=0;

    }


}
