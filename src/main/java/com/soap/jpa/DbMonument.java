package com.soap.jpa;


import org.locationtech.jts.geom.Point;

import javax.persistence.*;


@Entity
@Table(name = "monument")
public class DbMonument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    protected Point point;

    @Column
    protected String name;

    @Column
    protected String country;


    public String getName() {
        return name;
    }


    public void setName(String value) {
        this.name = value;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Point getPoint() {
        return point;
    }


}


