package com.keldorn;

import com.keldorn.dto.Employees;
import com.keldorn.model.burger.Meal;
import com.keldorn.model.employee.Employee;
import com.keldorn.model.employee.StoreEmployee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        employeeTest();
        burgerTest();
        employeeChallenge();
    }

    private static void separator() {
        System.out.println("-".repeat(30));
    }

    private static void employeeTest() {
        separator();
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001, "Ralph", 2015),
                new Employee(10005, "Carole", 2021),
                new Employee(10022, "Jane", 2013),
                new Employee(13151, "Laura", 2020),
                new Employee(10050, "Jim", 2018)
        ));

//        var comparator = new EmployeeComparator<>();
//        employees.sort(comparator);
        employees.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());

        for (var employee : employees) {
            System.out.println(employee);
        }

        System.out.println("Store Members");
        List<StoreEmployee> storeEmployees = getStoreEmployees();

        for (StoreEmployee e : storeEmployees) {
            System.out.println(e);
        }

        System.out.println("With Pig Latin Names");
        addPigLatinName(storeEmployees);
    }

    private static List<StoreEmployee> getStoreEmployees() {
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target")
        ));

        var comparator = new StoreEmployee().new StoreComparator<>();
        storeEmployees.sort(comparator);
        return storeEmployees;
    }

    private static void burgerTest() {
        separator();
        Meal regularMeal = new Meal();
        regularMeal.addToppings("Ketchup", "Mayo", "Bacon", "Cheddar");
        System.out.println(regularMeal);

        Meal USRegularMeal = new Meal(.68);
        System.out.println(USRegularMeal);
    }

    private static void addPigLatinName(List<? extends StoreEmployee> list) {
        final String lastName = "Piggy";

        class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee> {
            private final String pigLatinName;
            private final Employee originalInstance;

            public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
                this.pigLatinName = pigLatinName + " " + lastName;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString() {
                return originalInstance.toString() + " " + pigLatinName;
            }

            @Override
            public int compareTo(DecoratedEmployee o) {
                return pigLatinName.compareTo(o.pigLatinName);
            }
        }

        List<DecoratedEmployee> newList = new ArrayList<>(list.size());

        for (var employee : list) {
            String name = employee.getName();
            String pigLatin = name.substring(1) + name.charAt(0) + "ay";
            newList.add(new DecoratedEmployee(pigLatin, employee));
        }

        newList.sort(null);
        for (var dEmployee : newList) {
            System.out.println(dEmployee.originalInstance.getName() + " " + dEmployee.pigLatinName);
        }
    }

    private static void employeeChallenge() {
        separator();
        final List<Employees> employees = new ArrayList<>();

        employees.add(new Employees("John", "Test", LocalDate.parse("2021-02-03")));
        employees.add(new Employees("Ray", "Test", LocalDate.parse("2019-03-23")));
        employees.add(new Employees("Smith", "Test", LocalDate.parse("2022-11-15")));

        class ImprovedEmployee {
            private final Employees employee;

            public ImprovedEmployee(Employees employee) {
                this.employee = employee;
            }

            private String getFullName() {
                return employee.firstName() + " " + employee.lastName();
            }

            private int yearsWorked() {
                return LocalDate.now().getYear() - employee.hireDate().getYear();
            }

            @Override
            public String toString() {
                return "%s has been an employee for %d years".formatted(getFullName(), yearsWorked());
            }
        }

        final List<ImprovedEmployee> improvedEmployees = new ArrayList<>();

        for (var employee : employees) {
            improvedEmployees.add(new ImprovedEmployee(employee));
        }

        var comparator = new Comparator<ImprovedEmployee>() {
            @Override
            public int compare(ImprovedEmployee o1, ImprovedEmployee o2) {
                return o1.yearsWorked() - o2.yearsWorked();
            }
        };

        improvedEmployees.sort(comparator);

        for (var ie : improvedEmployees) {
            System.out.println(ie);
        }
    }
}
