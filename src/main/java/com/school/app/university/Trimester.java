package com.school.app.university;

import java.util.Map;

public class Trimester {
    private Map<String, String> teachers = Map.of(
            "Stacy Shawn", "PSU",
            "John Doe","SFSU",
            "Nancy Johnson","PSU",
            "Stuart Hunt","PSU",
            "Jenine Long","OSU",
            "Kathleen Brown", "OSU"
    );
    private Map<String, String> students = Map.of(
            "Lilly Wang","PSU",
            "Greg Mason","PSU",
            "Max Johnson","SFSU",
            "Phil Stanek","SFSU",
            "Kathy herbert","OSU",
            "Jill Addison", "OSU"
    );
    private String date;

    public Trimester(String date) {
        this.date = date;
    }

    public void printListOfTeachers() {
        System.out.println("List of teachers: ");
        int i = 0;
        for (Map.Entry<String,String> entry : this.teachers.entrySet()) {
            System.out.println(++i + ". " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public void printListOfStudents() {
        System.out.println("List of students: ");
        int i = 0;
        for (Map.Entry<String, String> entry : this.students.entrySet()) {
            System.out.println(++i + ". " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
