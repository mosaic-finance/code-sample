package com.school.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.school.app.course.Course;
import com.school.app.course.Quiz;
import com.school.app.exception.ForbiddenException;
import com.school.app.user.Student;
import com.school.app.user.Teacher;

public class CalculateGradePerStudentTest
{
    private Teacher John;
    private Course ELA101;
    private Course ELA102;
    private Quiz quiz1;
    private Quiz quiz2;
    private Quiz quiz3;
    private Quiz quiz4;
    private Quiz quiz5;
    private Student Max;
    private Student Lilly;

    @Before
    public void setup()
    {
        John = new Teacher("John Doe");
        ELA101 = new Course(5, John ,"ELA 101");
        ELA102 = new Course(5, John, "ELA 102");
        quiz1 = John.createQuiz(ELA101, "ELA 101 Quiz 1");
        quiz2 = John.createQuiz(ELA101, "ELA 101 Quiz 2");
        quiz3 = John.createQuiz(ELA101, "ELA 101 Quiz 3");
        quiz4 = John.createQuiz(ELA102, "ELA 102 Quiz 1");
        quiz5 = John.createQuiz(ELA102, "ELA 102 Quiz 2");
        Max = new Student("Max Johnson");
        Lilly = new Student("Lilly Wang");
        Max.enrollInCourse(ELA101);
        Max.enrollInCourse(ELA102);
        Lilly.enrollInCourse(ELA101);

        John.assignQuiz(ELA101, quiz1, Max);
        John.assignQuiz(ELA101, quiz2, Max);
        John.assignQuiz(ELA101, quiz3, Max);
        John.assignQuiz(ELA102, quiz4, Max);
        John.assignQuiz(ELA102, quiz5, Max);
        John.assignQuiz(ELA101, quiz1, Lilly);
        John.assignQuiz(ELA101, quiz2, Lilly);
        John.assignQuiz(ELA101, quiz3, Lilly);

        Max.updateQuizGrade(quiz1, 3);
        Max.updateQuizGrade(quiz2, 2);
        Max.updateQuizGrade(quiz3, 0);
        Max.updateQuizGrade(quiz4, 5);
        Max.updateQuizGrade(quiz5, 1);

        Lilly.updateQuizGrade(quiz1, 2);
        Lilly.updateQuizGrade(quiz2, 4);
        Lilly.updateQuizGrade(quiz3, 4);
    }

    @Test
    public void test_calculateStudentGradeForOneCourse()
    {
        Assert.assertEquals(John.calculateStudentGradeForOneCourse(Max, ELA101) , 5);
        Assert.assertEquals(John.calculateStudentGradeForOneCourse(Max, ELA102), 6);
        Assert.assertEquals(John.calculateStudentGradeForOneCourse(Lilly, ELA101) , 10);
    }

    @Test
    public void test_calculateStudentGradeForAllSemesterCourses()
    {
        Assert.assertEquals(John.calculateGradesForStudent(Max), 11);
        Assert.assertEquals(John.calculateGradesForStudent(Lilly), 10);
    }

    @Test(expected = ForbiddenException.class)
    public void calculateStudentGradeForStudentNotEnrolled()
    {
        John.calculateStudentGradeForOneCourse(Lilly, ELA102);
    }

}
