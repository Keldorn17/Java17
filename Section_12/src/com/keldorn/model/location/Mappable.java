package com.keldorn.model.location;

public interface Mappable {
    void render();

    default String getExplicitName() {
        return getClass().getSuperclass().getSimpleName().toUpperCase();
    }
}
