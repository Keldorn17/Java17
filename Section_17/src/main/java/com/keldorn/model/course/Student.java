package main.java.com.keldorn.model.course;

import main.java.com.keldorn.model.course.dto.Course;

import java.time.LocalDate;
import java.util.*;

public final class Student {
    private final static Random random = new Random();

    private final UUID studentId = UUID.randomUUID();
    private final String countryCode;
    private final int yearEnrolled;
    private final int ageEnrolled;
    private final Gender gender;
    private final boolean programmingExperience;
    private final Map<String, CourseEngagement> engagementMap = new HashMap<>();

    public Student(String countryCode, int yearEnrolled, int ageEnrolled, Gender gender,
                   boolean programmingExperience, Course... courses) {
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.programmingExperience = programmingExperience;

        for (Course course : courses) {
            addCourse(course, LocalDate.of(yearEnrolled, 1, 1));
        }
    }

    public void addCourse(Course newCourse, LocalDate enrollDate) {
        engagementMap.put(newCourse.courseCode(), new CourseEngagement(newCourse, enrollDate, "Enrollment"));
    }

    public void addCourse(Course newCourse) {
        addCourse(newCourse, LocalDate.now());
    }

    public UUID getStudentId() {
        return studentId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public int getAgeEnrolled() {
        return ageEnrolled;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean hasProgrammingExperience() {
        return programmingExperience;
    }

    public Map<String, CourseEngagement> getEngagementMap() {
        return Map.copyOf(engagementMap);
    }

    public int getYearsSinceEnrolled() {
        return LocalDate.now().getYear() - yearEnrolled;
    }

    public int getAge() {
        return ageEnrolled + getYearsSinceEnrolled();
    }

    public int getMonthsSinceActive(String courseCode) {
        var info = engagementMap.get(courseCode);
        return info == null ? 0 : info.getMonthsSinceActive();
    }

    public int getMonthsSinceActive() {
        int inactiveMonths = (LocalDate.now().getYear() - 2014) * 12;
        for (String key : engagementMap.keySet()) {
            inactiveMonths = Math.min(inactiveMonths, getMonthsSinceActive(key));
        }
        return inactiveMonths;
    }

    public double getPercentComplete(String courseCode) {
        var info = engagementMap.get(courseCode);
        return (info == null) ? 0 : info.getPercentComplete();
    }

    public void watchLecture(String courseCode, int lectureNumber, int month, int year) {
        var activity = engagementMap.get(courseCode);
        if (activity != null) {
            activity.watchLecture(lectureNumber, LocalDate.of(year, month, 1));
        }
    }

    private static String getRandomVal(String... data) {
        return data[random.nextInt(data.length)];
    }

    private static Gender getRandomGender() {
        return Gender.values()[random.nextInt(Gender.values().length)];
    }

    public static Student getRandomStudent(Course... courses) {
        int maxYear = LocalDate.now().getYear() + 1;

        Student student = new Student(
                getRandomVal("AU", "CA", "CN", "GB", "IN", "UA", "US"),
                random.nextInt(2015, maxYear),
                random.nextInt(18, 90),
                getRandomGender(),
                random.nextBoolean(),
                courses);

        for (Course c : courses) {
            int lecture = random.nextInt(1, c.lectureCount());
            int year = random.nextInt(student.getYearEnrolled(), maxYear);
            int month = random.nextInt(1, 13);
            if (year == (maxYear - 1)) {
                if (month > LocalDate.now().getMonthValue()) {
                    month = LocalDate.now().getMonthValue();
                }
            }
            student.watchLecture(c.courseCode(), lecture, month, year);
        }

        return student;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", countryCode='" + countryCode + '\'' +
                ", yearEnrolled=" + yearEnrolled +
                ", ageEnrolled=" + ageEnrolled +
                ", gender=" + gender +
                ", programmingExperience=" + programmingExperience +
                ", engagementMap=" + engagementMap +
                '}';
    }
}
