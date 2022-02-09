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
    private Teacher Stacy;
    private Teacher Stuart;
    private Course ELA101;
    private Course ELA102;
    private Quiz quiz1;
    private Quiz quiz2;
    private Quiz quiz3;
    private Quiz quiz4;
    private Quiz quiz5;
    private Student Greg;
    private Student Lilly;

    @Before
    public void setup()
    {
        Stacy = new Teacher("Stacy Shawn");
        Stuart = new Teacher("Stuart Hun");
        ELA101 = new Course(5, Stacy,"ELA 101");
        ELA102 = new Course(5, Stuart, "ELA 102");
        quiz1 = Stacy.createQuiz(ELA101, "ELA 101 Quiz 1");
        quiz2 = Stacy.createQuiz(ELA101, "ELA 101 Quiz 2");
        quiz3 = Stacy.createQuiz(ELA101, "ELA 101 Quiz 3");
        quiz4 = Stuart.createQuiz(ELA102, "ELA 102 Quiz 1");
        quiz5 = Stuart.createQuiz(ELA102, "ELA 102 Quiz 2");
        Greg = new Student("Greg Mason");
        Lilly = new Student("Lilly Wang");
        Greg.enrollInCourse(ELA101);
        Greg.enrollInCourse(ELA102);
        Lilly.enrollInCourse(ELA101);

        Stacy.assignQuiz(ELA101, quiz1, Greg);
        Stacy.assignQuiz(ELA101, quiz2, Greg);
        Stacy.assignQuiz(ELA101, quiz3, Greg);
        Stuart.assignQuiz(ELA102, quiz4, Greg);
        Stuart.assignQuiz(ELA102, quiz5, Greg);
        Stacy.assignQuiz(ELA101, quiz1, Lilly);
        Stacy.assignQuiz(ELA101, quiz2, Lilly);
        Stacy.assignQuiz(ELA101, quiz3, Lilly);

        Greg.updateQuizGrade(quiz1, 3);
        Greg.updateQuizGrade(quiz2, 2);
        Greg.updateQuizGrade(quiz3, 0);
        Greg.updateQuizGrade(quiz4, 5);
        Greg.updateQuizGrade(quiz5, 1);

        Lilly.updateQuizGrade(quiz1, 2);
        Lilly.updateQuizGrade(quiz2, 4);
        Lilly.updateQuizGrade(quiz3, 4);
    }

    @Test
    public void test_calculateStudentGradeForOneCourse()
    {
        Assert.assertEquals(Stacy.calculateStudentGradeForOneCourse(Greg, ELA101) , 5);
        Assert.assertEquals(Stacy.calculateStudentGradeForOneCourse(Greg, ELA102), 6);
        Assert.assertEquals(Stacy.calculateStudentGradeForOneCourse(Lilly, ELA101) , 10);
    }

    @Test
    public void test_calculateStudentGradeForAllSemesterCourses()
    {
        Assert.assertEquals(Stacy.calculateGradesForStudent(Greg), 5);
        Assert.assertEquals(Stacy.calculateGradesForStudent(Lilly), 10);
    }

    @Test(expected = ForbiddenException.class)
    public void calculateStudentGradeForStudentNotEnrolled()
    {
        Stacy.calculateStudentGradeForOneCourse(Lilly, ELA102);
    }

}
