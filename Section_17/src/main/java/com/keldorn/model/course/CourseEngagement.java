package main.java.com.keldorn.model.course;

import main.java.com.keldorn.model.course.dto.Course;

import java.time.LocalDate;
import java.time.Period;

public final class CourseEngagement {
    private final Course course;
    private final LocalDate enrollmentDate;
    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType) {
        this.course = course;
        this.enrollmentDate = this.lastActivityDate = enrollmentDate;
        this.engagementType = engagementType;
    }

    public CourseEngagement(Course course, String engagementType) {
        this(course, LocalDate.now(), engagementType);
    }

    public String getCourseCode() {
        return course.courseCode();
    }

    public int getEnrollmentYear() {
        return enrollmentDate.getYear();
    }

    public int getLastActivityYear() {
        return lastActivityDate.getYear();
    }

    public String getLastActivityMonth() {
        return "%tb".formatted(lastActivityDate);
    }

    public int getMonthsSinceActive() {
//        return Math.toIntExact(ChronoUnit.MONTHS.between(lastActivityDate, LocalDate.now()));
        Period period =  Period.between(lastActivityDate, LocalDate.now());
        return (int) period.toTotalMonths();
    }

    public double getPercentComplete() {
        return lastLecture / (double) course.lectureCount() * 100;
    }

    void watchLecture(int lectureNumber, LocalDate date) {
        lastLecture = Math.max(lectureNumber, lastLecture);
        lastActivityDate = date;
        engagementType = "Lecture " + lastLecture;
    }

    @Override
    public String toString() {
        return "%s: %s %d %s [%d]".formatted(course.courseCode(),
                getLastActivityMonth(), getLastActivityYear(), engagementType,
                getMonthsSinceActive());
    }
}
