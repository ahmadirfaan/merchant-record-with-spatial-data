package com.irfaan.entities;

import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;

public class SubCounty {

    private Integer id;
    private String name;
    private MultiPoint geom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultiPoint getGeom() {
        return geom;
    }

    public void setGeom(MultiPoint geom) {
        this.geom = geom;
    }
}
