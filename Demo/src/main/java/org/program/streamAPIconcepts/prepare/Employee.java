package org.program.streamAPIconcepts.prepare;

public class Employee {
    private String jobTitle;
    private double salary;

    public Employee(String jobTitle, double salary) {
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public double getSalary() {
        return salary;
    }
}

