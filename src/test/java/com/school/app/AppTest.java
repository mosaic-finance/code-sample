package com.school.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({EnrollTest.class,
                     CreateQuizTest.class,
                     AssignQuizTest.class,
                     TakeQuizTest.class,
                     CalculateGradePerStudentTest.class})
public class AppTest
{
}
