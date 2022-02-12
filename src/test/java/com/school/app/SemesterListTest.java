package com.school.app;

import com.school.app.university.Trimester;
import org.junit.Test;

public class SemesterListTest {

    private Trimester springTrimester = new Trimester("Spring Trimester 2022");

    @Test
    public void test_listStudentsAndTeachers() {
        springTrimester.printListOfTeachers();
        springTrimester.printListOfStudents();
    }
}
