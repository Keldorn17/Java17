package com.keldorn.model.map;

import com.keldorn.model.map.enums.Geometry;
import com.keldorn.model.map.enums.UsageType;

public class Building implements Mappable {
    private final String name;
    private final UsageType buildingType;
    private final BaseMap baseMap;

    public Building(String name, UsageType buildingType, BaseMap baseMap) {
        this.name = name;
        this.buildingType = buildingType;
        this.baseMap = baseMap;
    }

    @Override
    public String getLabel() {
        return String.format("%s (%s)", name, buildingType);
    }

    @Override
    public String getMarker() {
        return baseMap.getMarker();
    }

    @Override
    public Geometry getShape() {
        return baseMap.getGeometry();
    }

    @Override
    public String toJSON() {
        StringBuilder JSON = new StringBuilder();
        JSON.append(Mappable.super.toJSON());
        JSON.deleteCharAt(JSON.indexOf("}"));

        JSON.append(String.format(", \"name\": \"%s\", \"usage\": \"%s\"}", name, buildingType));
        return JSON.toString();
    }
}
