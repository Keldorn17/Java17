package main.java.com.keldorn.model.course;

import java.time.LocalDate;

public record Purchase(String courseId, int studentId, double price, int yr, int dayOfYear) {

    public LocalDate purchaseData() {
        return LocalDate.ofYearDay(yr, dayOfYear);
    }
}
