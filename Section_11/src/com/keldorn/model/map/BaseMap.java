package com.keldorn.model.map;

import com.keldorn.model.map.enums.Color;
import com.keldorn.model.map.enums.Geometry;
import com.keldorn.model.map.enums.LineMarker;
import com.keldorn.model.map.enums.PointMarker;

public class BaseMap {
    private final Color color;
    private final Geometry geometry;
    private PointMarker pointMarker = null;
    private LineMarker lineMarker = null;

    public BaseMap(Color color, PointMarker pointMarker) {
        this.color = color;
        this.geometry = Geometry.POINT;
        this.pointMarker = pointMarker;
    }

    public BaseMap(Color color, LineMarker lineMarker) {
        this.color = color;
        this.geometry = Geometry.LINE;
        this.lineMarker = lineMarker;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getMarker() {
        return String.format("%s %s", color, lineMarker == null ? pointMarker : lineMarker);
    }
}
