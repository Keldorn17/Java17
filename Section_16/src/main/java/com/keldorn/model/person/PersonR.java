package main.java.com.keldorn.model.person;

import java.util.Arrays;

public record PersonR(String name, String dob, PersonR[] kids) {
    public PersonR(PersonR p) {
        this(p.name, p.dob, p.kids == null ? null :
                Arrays.copyOf(p.kids, p.kids.length));
    }

    @Override
    public String toString() {
        return "PersonR{" +
                "name='" + name + '\'' +
                ", kids=" + Arrays.toString(kids) +
                '}';
    }
}
