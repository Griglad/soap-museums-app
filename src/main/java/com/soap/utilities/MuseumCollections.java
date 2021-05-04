package com.soap.utilities;

import com.soap.jpa.DbMuseum;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public final class MuseumCollections {
    private static final Set<DbMuseum> dbMuseums = new LinkedHashSet<>();
    private static final Map<DbMuseum, Double> museumDistanceOnMap = new HashMap<>();


    public static Set<DbMuseum> getDbMuseumSet() {
        return dbMuseums;
    }

    public static Map<DbMuseum, Double> getMuseumDistanceOnMap() {
        return museumDistanceOnMap;
    }
}
