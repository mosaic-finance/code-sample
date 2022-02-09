package com.school.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.school.app.course.Course;
import com.school.app.course.Quiz;
import com.school.app.exception.ForbiddenException;
import com.school.app.user.Student;
import com.school.app.user.Teacher;

public class AssignQuizTest
{
    private Teacher John;
    private Course ELA101;
    private Quiz quiz;
    private Student Max;
    private Student Phil;

    @Before
    public void setUp()
    {
        John = new Teacher("John Doe");
        ELA101 = new Course(10, John, "ELA 101");
        quiz = John.createQuiz(ELA101, "ELA 101 Quiz 1");
        Max = new Student("Max Johnson");
        Phil = new Student("Phil Stanek");
        Max.enrollInCourse(ELA101);
    }

    @Test
    public void test_assignQuizToEnrolledStudent()
    {
        John.assignQuiz(ELA101, quiz, Max);
        Assert.assertTrue(Max.canTakeQuiz(quiz));
    }

    @Test(expected = ForbiddenException.class)
    public void test_assignQuizToUnenrolledStudent()
    {
        John.assignQuiz(ELA101, quiz, Phil);
    }
}
