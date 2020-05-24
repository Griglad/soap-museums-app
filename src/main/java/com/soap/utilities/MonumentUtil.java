package com.soap.utilities;

import com.soap.jpa.DbMonument;
import com.soap.model.Monument;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MonumentUtil {


    private static Set<Monument> monuments = new LinkedHashSet<>();


    public static Set<Monument> getMonuments() {
        return monuments;
    }

    public static Monument fromdbToMonument(DbMonument dbMonument) {
        Monument monument = new Monument();
        monument.setName(dbMonument.getName());
        monument.setLatitude(dbMonument.getPoint().getX());
        monument.setLongitude(dbMonument.getPoint().getY());
        monument.setCountry(dbMonument.getCountry());
        return monument;
    }

    public static DbMonument retrieveMinDistanceMonument(Map<DbMonument, Double> monumentDoubleMap) {

        DbMonument monument = Collections.min(monumentDoubleMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        return monument;

    }

    public static void update(Monument monument) {
        long counter = monument.getCounter();
        monument.setCounter(++counter);
    }


}
