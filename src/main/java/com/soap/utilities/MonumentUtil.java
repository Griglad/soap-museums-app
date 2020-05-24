package com.soap.utilities;

import com.soap.dao.MonumentDaoImpl;
import com.soap.jpa.DbMonument;
import com.soap.model.Monument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
@Component
public class MonumentUtil {


    private static MonumentDaoImpl monumentDaoImpl;

    private static List<Monument> monuments = new ArrayList<>();

    @Autowired
    public MonumentUtil(MonumentDaoImpl monumentDaoImpl){
        this.monumentDaoImpl = monumentDaoImpl;
    }


    public static List<Monument> getMonuments() {
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

    public static DbMonument retrieveMinDistance(Map<DbMonument, Double> monumentDoubleMap) {

        DbMonument monument = Collections.min(monumentDoubleMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        return monument;
    }

    public static void update(Monument monument) {
        long counter = monument.getCounter();
        monument.setCounter(++counter);
    }






}
