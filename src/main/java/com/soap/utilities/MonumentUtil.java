package com.soap.utilities;

import com.soap.jpa.DbMonument;
import com.soap.model.Monument;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Grigorios Ladas
 */
public class MonumentUtil {


    private static Set<DbMonument> dbMonuments = new LinkedHashSet<>();

    public static Set<DbMonument> getMonuments() {

        return dbMonuments;
    }


    public static Monument fromdbToMonument(DbMonument dbMonument) {
        Monument monument = new Monument();
        monument.setName(dbMonument.getName());
        monument.setLatitude(dbMonument.getPoint().getX());
        monument.setLongitude(dbMonument.getPoint().getY());
        monument.setRegion(dbMonument.getRegion());
        monument.setDescription(dbMonument.getDescription());
        return monument;
    }

    public static DbMonument fromMonumentToDb(Monument monument) {
        DbMonument dbmonument = new DbMonument();
        dbmonument.setName(monument.getName());
        dbmonument.setPoint(GeometryHelper.createPoint(monument.getLatitude(), monument.getLongitude()));
        dbmonument.setRegion(monument.getRegion());
        return dbmonument;
    }

    public static DbMonument retrieveMinDistanceMonument(Map<DbMonument, Double> monumentDoubleMap) {

        DbMonument monument = Collections.min(monumentDoubleMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        return monument;

    }


    public static void updateCounter(DbMonument dbMonument) {
        long counter = dbMonument.getCounter();
        dbMonument.setCounter(++counter);
    }


}
