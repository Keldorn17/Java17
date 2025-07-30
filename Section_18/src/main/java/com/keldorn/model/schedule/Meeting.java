package main.java.com.keldorn.model.schedule;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public final class Meeting {
    private final Employee employee1;
    private final Employee employee2;

    public Meeting(Employee employee1, Employee employee2) {
        this.employee1 = employee1;
        this.employee2 = employee2;
    }

    public void printPossibleMeetings(int inDays) {
        Instant now = Instant.now();
        ZonedDateTime emp1Time = ZonedDateTime.ofInstant(now, employee1.zoneId())
                .plusDays(1)
                .withHour(7).withMinute(0).withSecond(0).withNano(0);

        ZonedDateTime endTime = emp1Time.plusDays(inDays);

        while (emp1Time.isBefore(endTime)) {
            ZonedDateTime emp2Time = emp1Time.withZoneSameInstant(employee2.zoneId());

            if (validDayToCheck(emp1Time.toLocalDateTime()) && validDayToCheck(emp2Time.toLocalDateTime())) {
                System.out.printf("%s <---> %s%n", employeeData(employee1, emp1Time), employeeData(employee2, emp2Time));
            }

            emp1Time = emp1Time.plusHours(1);
        }
    }

    private boolean betweenHours(LocalTime localTime) {
        return betweenHours(localTime, 7, 20);
    }

    private boolean betweenHours(LocalTime localTime, int startHour, int endHour) {
        return localTime.getHour() >= startHour && localTime.getHour() <= endHour;
    }

    private boolean isWeekend(LocalDate localDate) {
        var dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean validDayToCheck(LocalDateTime localDateTime) {
        return !isWeekend(localDateTime.toLocalDate()) && betweenHours(localDateTime.toLocalTime());
    }

    private String employeeData(Employee employee, ZonedDateTime zdt) {
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT);
        return employee.getDateInfo(zdt, dtf);
    }
}
