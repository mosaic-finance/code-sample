package com.school.app;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.school.app.course.Course;
import com.school.app.course.MultipleChoiceQuestion;
import com.school.app.course.Quiz;
import com.school.app.exception.ForbiddenException;
import com.school.app.user.Teacher;

public class CreateQuizTest
{
    private Teacher John;
    private Course ELA101;

    @Before
    public void setUp()
    {
        John = new Teacher("John Doe");
        ELA101 = new Course(10, John, "ELA 101");
    }

    @Test
    public void test_createMultipleQuizzes()
    {
        Quiz quiz1 = John.createQuiz(ELA101, "ELA 101 Quiz 1");
        Assert.assertTrue(ELA101.hasQuiz(quiz1));
        Quiz quiz2 = John.createQuiz(ELA101, "ELA 101 Quiz 2");
        Assert.assertTrue(ELA101.hasQuiz(quiz2));
    }

    @Test
    public void test_createQuizWithMultipleQuestions()
    {
        MultipleChoiceQuestion questionSky = new MultipleChoiceQuestion("What color is the sky?",
                                                                        Arrays.asList("blue", "green", "red"),
                                                                        "blue");
        MultipleChoiceQuestion questionGrass = new MultipleChoiceQuestion("What color is the grass?",
                                          Arrays.asList("blue","green","red","pink"),
                                          "green");
        MultipleChoiceQuestion questionWood = new MultipleChoiceQuestion("What color is wood?",
                                                                         Arrays.asList("blue", "green", "red", "black", "brown"),
                                                                         "brown");
        Quiz quiz = John.createQuiz(ELA101, "ELA 101 Quiz 3");
        quiz.addQuestion(questionSky);
        quiz.addQuestion(questionGrass);
        quiz.addQuestion(questionWood);
        Assert.assertEquals(quiz.getNumberOfQuestions(),3);
    }

    @Test(expected = ForbiddenException.class)
    public void test_createQuizWithoutPermission()
    {
        Teacher Kathleen = new Teacher("Kathleen Brown");
        Kathleen.createQuiz(ELA101, "ELA 101 Kathleen's Quiz");
    }

    @Test(expected = ForbiddenException.class)
    public void test_createQuizWithSameQuizName()
    {
        John.createQuiz(ELA101, "John's quiz");
        John.createQuiz(ELA101,"John's quiz");
    }
}
