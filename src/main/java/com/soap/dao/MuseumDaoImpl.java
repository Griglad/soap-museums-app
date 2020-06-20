package com.soap.dao;

import com.soap.jpa.DbMuseum;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class MuseumDaoImpl implements MuseumDao {

    private EntityManager manager;


    public MuseumDaoImpl(EntityManager manager) {
        this.manager = manager;

    }


    @SuppressWarnings("unchecked")
    public List<DbMuseum> getAllDbMuseums() {

        Query query = manager.createNativeQuery("select * from museum", DbMuseum.class);

        return query.getResultList();

    }

    @Override
    public void addDbMuseum(DbMuseum inputDbMuseum) {


        manager.persist(inputDbMuseum);


    }

    @Override
    public DbMuseum findMuseum(String name) {
        Query query = manager.createNativeQuery("select * from museum where lower(museum.name) = :name", DbMuseum.class);
        query.setParameter("name", name);
        Optional resultListQuery = query.getResultList().stream().findFirst();
        if (resultListQuery.isPresent()) {
            return (DbMuseum) resultListQuery.get();
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DbMuseum> findMuseumsByPlace(String place) {
        Query query = manager.createNativeQuery("select * from museum where lower(museum.place) = :place", DbMuseum.class);
        query.setParameter("place", place);
        return query.getResultList();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DbMuseum> findMuseumsByRegion(String region) {
        Query query = manager.createNativeQuery("select * from museum where lower(museum.region) = :region", DbMuseum.class);
        query.setParameter("region", region);
        return query.getResultList();
    }

    @Override
    public void updateMuseum(DbMuseum dbMuseum) {

        manager.merge(dbMuseum);

    }

    @Override
    public void deleteMuseum(DbMuseum dbMuseum) {

        manager.remove(manager.contains(dbMuseum) ? dbMuseum : manager.merge(dbMuseum));


    }


}
