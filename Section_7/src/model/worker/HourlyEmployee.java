package model.worker;

public class HourlyEmployee extends Employee{

    private final double hourlyPayRate;

    public HourlyEmployee(String name, String birthDate,
                          long employeeId, String hireDate, double hourlyPayRate) {
        super(name, birthDate, employeeId, hireDate);
        this.hourlyPayRate = hourlyPayRate;
    }

    public double getDoublePay() {
        return collectPay() *  2;
    }

    @Override
    public double collectPay() {
        int workingHour = 8;
        int workDays = 21;
        return hourlyPayRate * workDays * workingHour;
    }

    @Override
    public String toString() {
        return "HourlyEmployee{" +
                "hourlyPayRate=" + hourlyPayRate +
                "} " + super.toString();
    }
}
