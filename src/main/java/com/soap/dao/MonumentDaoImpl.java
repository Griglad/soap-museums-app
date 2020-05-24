package com.soap.dao;

import com.soap.jpa.DbMonument;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class MonumentDaoImpl implements MonumentDao {

    private EntityManager manager;


    public MonumentDaoImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Cacheable("monuments")
    public List<DbMonument> getAllDbMonuments() {

        Query query = manager.createNativeQuery("select * from monument", DbMonument.class);

        return query.getResultList();
    }


}
