package com.soap.jpa;


import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "monument")
public class DbMonument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private Point point;

    @Column
    private String name;

    @Column
    private String country;

    @Transient
    private long counter;


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

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbMonument that = (DbMonument) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}


