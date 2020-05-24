package com.soap.dao;

import com.soap.jpa.DbMonument;

import java.util.List;

public interface MonumentDao {

    List<DbMonument> getAllDbMonuments();
}
