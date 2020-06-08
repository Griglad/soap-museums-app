package com.soap.dao;

import com.soap.jpa.DbMonument;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class MonumentDaoImpl implements MonumentDao {

    private EntityManager manager;


    public MonumentDaoImpl(EntityManager manager) {
        this.manager = manager;

    }


    @SuppressWarnings("unchecked")
    public List<DbMonument> getAllDbMonuments() {

        Query query = manager.createNativeQuery("select * from monument", DbMonument.class);

        return query.getResultList();

    }

    @Override
    public void addDbMonument(DbMonument inputDbMonument) {


        manager.persist(inputDbMonument);


    }

    @Override
    public DbMonument findMonument(String name) {
        Query query = manager.createNativeQuery("select * from monument where lower(monument.name) = :name", DbMonument.class);
        query.setParameter("name", name);
        Optional resultListQuery = query.getResultList().stream().findFirst();
        if (resultListQuery.isPresent()) {
            return (DbMonument) resultListQuery.get();
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DbMonument> findMonumentsByRegion(String region) {
        Query query = manager.createNativeQuery("select * from monument where lower(monument.region) = :country", DbMonument.class);
        query.setParameter("country", region);
        return query.getResultList();
    }

    @Override
    public void updateMonument(DbMonument dbmonument) {

        manager.merge(dbmonument);

    }

    @Override
    public void deleteMonument(DbMonument dbMonument) {

        manager.remove(manager.contains(dbMonument)?dbMonument: manager.merge(dbMonument));


    }


}
