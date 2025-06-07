package com.keldorn.model.map;

import com.keldorn.model.map.enums.Geometry;
import com.keldorn.model.map.enums.UtilityType;

public class UtilityLine implements Mappable {
    private final String name;
    private final UtilityType utilityType;
    private final BaseMap baseMap;

    public UtilityLine(String name, UtilityType utilityType, BaseMap baseMap) {
        this.name = name;
        this.utilityType = utilityType;
        this.baseMap = baseMap;
    }

    @Override
    public String getLabel() {
        return String.format("%s (%s)", name, utilityType);
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

        JSON.append(String.format(", \"name\": \"%s\", \"utility\": \"%s\"}", name, utilityType));
        return JSON.toString();
    }
}
