package com.soap.dao;

import com.soap.jpa.DbMonument;
import java.util.List;

public interface MonumentDao {

    List<DbMonument> getAllDbMonuments();

    void addDbMonument(DbMonument dbMonument);

    DbMonument findMonument(String name);

    List<DbMonument> findMonumentsByRegion(String country);


    void updateMonument(DbMonument dbMonument);


    void deleteMonument(DbMonument dbMonument);

}
