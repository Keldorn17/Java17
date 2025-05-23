package model.worker;

public class SalariedEmployee extends Employee{

    private final double annualSalary;
    private boolean isRetired;

    public SalariedEmployee(String name, String birthDate, long employeeId,
                            String hireDate, double annualSalary, boolean isRetired) {
        super(name, birthDate, employeeId, hireDate);
        this.annualSalary = annualSalary;
        this.isRetired = isRetired;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public boolean isRetired() {
        return isRetired;
    }

    public void retire() {
        this.isRetired = true;
    }

    @Override
    public double collectPay() {
        System.out.println("Annual salary has been collected");
        return annualSalary;
    }

    @Override
    public String toString() {
        return "SalariedEmployee{" +
                "annualSalary=" + annualSalary +
                ", isRetired=" + isRetired +
                "} " + super.toString();
    }
}
