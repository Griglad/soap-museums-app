package com.soap.dao;

import com.soap.jpa.DbMuseum;
import java.util.List;

public interface MuseumDao {

    List<DbMuseum> getAllDbMuseums();

    void addDbMuseum(DbMuseum dbMuseum);

    DbMuseum findMuseum(String name);

    List<DbMuseum> findMuseumsByRegion(String region);

    List<DbMuseum> findMuseumsByPlace(String place);

    void updateMuseum(DbMuseum dbMuseum);


    void deleteMuseum(DbMuseum dbMuseum);


}
