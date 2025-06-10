package com.keldorn.dto;

import java.time.LocalDate;

public record Employees(String firstName, String lastName, LocalDate hireDate) {
}
