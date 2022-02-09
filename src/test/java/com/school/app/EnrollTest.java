package com.school.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.school.app.course.Course;
import com.school.app.exception.ForbiddenException;
import com.school.app.user.Student;
import com.school.app.user.Teacher;

public class EnrollTest
{
    private Teacher John;
    private Teacher Kathleen;
    private Course ELA101;
    private Course ELA102;
    private Student Max;
    private Student Lilly;
    private Student Phil;
    private Student Kevin;

    @Before
    public void setup()
    {
        John = new Teacher("John Doe");
        Kathleen = new Teacher("Kathleen Brown");
        ELA101 = new Course(3, John ,"ELA 101");
        ELA102 = new Course(1,Kathleen, "ELA 102");
        Max = new Student("Max Johnson");
        Lilly = new Student("Lilly Wang");
        Phil = new Student("Phil Stanek");
        Kevin = new Student("Kevin Bower");
    }

    // Student can enroll in class when there is space
    @Test
    public void test_enrollUserIfSpaceInClass()
    {
        Max.enrollInCourse(ELA101);
        Assert.assertTrue("verifying student is in the course",ELA101.isEnrolledInClass(Max));
        Lilly.enrollInCourse(ELA101);
        Assert.assertTrue("verifying student is in the course",ELA101.isEnrolledInClass(Lilly));
        Phil.enrollInCourse(ELA101);
        Assert.assertTrue("verifying student is in the course",ELA101.isEnrolledInClass(Phil));
    }

    @Test(expected = ForbiddenException.class)
    public void test_enrollUserWhenClassIsFull()
    {
        Max.enrollInCourse(ELA102);
        Assert.assertTrue("verifying student is in the course",ELA102.isEnrolledInClass(Max));
        Kevin.enrollInCourse(ELA102);
    }

}

