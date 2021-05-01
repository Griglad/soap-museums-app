package com.soap.jpa;


import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "museum")
public class DbMuseum {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column
    private Point point;

    @Column
    private String name;

    @Column
    private String region;

    @Column
    private String place;

    @Column(columnDefinition = "text")
    private String description;

    @Transient
    private long counter;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String value) {
        this.name = value;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbMuseum that = (DbMuseum) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(region, that.region) &&
                Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, region, place);
    }

    @Override
    public String toString() {
        return "DbMuseum{" +
                "point=" + point +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                '}' + "\n";
    }
}



