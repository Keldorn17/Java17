package com.keldorn.model.exercise.savable;

import java.util.List;

public interface ISavable {
    List<String> write();
    void read(List<String> list);
}
