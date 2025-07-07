package main.java.com.keldorn.model.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {

    public static int lastId = 1;

    private final String name;
    private final int id;
    private final List<Course> coursesList;

    public Student(String name, List<Course> coursesList) {
        this.name = name;
        this.coursesList = coursesList;
        id = lastId++;
    }

    public Student(String name, Course course) {
        this(name, new ArrayList<>(List.of(course)));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addCourse(Course course) {
        coursesList.add(course);
    }

    @Override
    public String toString() {
        String[] courseNames = new String[coursesList.size()];
        Arrays.setAll(courseNames, i -> coursesList.get(i).name());
        return "[%d] : %s".formatted(id, String.join(", ", courseNames));
    }
}
