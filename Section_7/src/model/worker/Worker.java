package model.worker;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

public abstract class Worker {

    protected final String name;
    protected final String birthDate;
    protected String endDate;

    public Worker(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getAge() {
        int currentYear = ZonedDateTime.now().getYear();
        ZonedDateTime employeesBirthDate;
        try {
            employeesBirthDate = ZonedDateTime.parse(birthDate);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return currentYear - employeesBirthDate.getYear();
    }

    public abstract double collectPay();

    public void terminate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Worker{" + "name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
