package com.keldorn.model.map;

import com.keldorn.model.map.enums.Geometry;

public interface Mappable {
    String JSON_PROPERTY = "\"properties\":{%s}";

    String getLabel();
    String getMarker();
    Geometry getShape();

    default String toJSON() {
        String innerJSON = String.format("\"type\": \"%s\", \"label\": \"%s\", \"marker\": \"%s\"", getShape(), getLabel(), getMarker());
        return String.format(JSON_PROPERTY, innerJSON);
    }

    static void mapIt(Mappable mappable) {
        System.out.println(mappable.toJSON());
    }
}
