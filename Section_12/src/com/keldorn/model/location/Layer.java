package com.keldorn.model.location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Layer<T extends Mappable> {
    private final List<T> layerElements = new ArrayList<>();

    @SafeVarargs
    public Layer(T... layerElements) {
        this.layerElements.addAll(Arrays.asList(layerElements));
    }

    @SafeVarargs
    public final void addElements(T... elements) {
        layerElements.addAll(Arrays.asList(elements));
    }

    public void addElements(List<T> elements) {
        layerElements.addAll(elements);
    }

    public void addElement(T element) {
        this.addElements(element);
    }

    public void renderLayer() {
        for (var element : layerElements) {
            element.render();
        }
    }
}
