package com.school.app;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.school.app.course.Course;
import com.school.app.course.MultipleChoiceQuestion;
import com.school.app.course.Quiz;
import com.school.app.course.QuizAttempt;
import com.school.app.exception.ForbiddenException;
import com.school.app.user.Student;
import com.school.app.user.Teacher;

public class TakeQuizTest
{
    private Teacher John;
    private Course ELA101;
    private Quiz quiz;
    private Student Max;
    private Student Phil;
    private String[] QUESTIONS = {"What color is the sky?", "What color is the grass?", "What color is wood?"};
    private List<String> ANSWER_SKY = Arrays.asList("blue", "green", "red");
    private List<String> ANSWER_GRASS = Arrays.asList("blue","green","red","pink");
    private List<String> ANSWER_WOOD = Arrays.asList("blue", "green", "red", "black", "brown");
    private String[] CORRECT_ANSWERS = {"blue", "green", "brown"};

    @Before
    public void setUp()
    {
        John = new Teacher("John Doe");
        ELA101 = new Course(10, John, "ELA 101");
        quiz = John.createQuiz(ELA101, "ELA 101 Quiz 1");
        MultipleChoiceQuestion questionSky = new MultipleChoiceQuestion(QUESTIONS[0], ANSWER_SKY,CORRECT_ANSWERS[0]);
        MultipleChoiceQuestion questionGrass = new MultipleChoiceQuestion(QUESTIONS[1], ANSWER_GRASS, CORRECT_ANSWERS[1]);
        MultipleChoiceQuestion questionWood = new MultipleChoiceQuestion(QUESTIONS[2], ANSWER_WOOD, CORRECT_ANSWERS[2]);
        quiz.addQuestion(questionSky);
        quiz.addQuestion(questionGrass);
        quiz.addQuestion(questionWood);
        Max = new Student("Max Johnson");
        Phil = new Student("Phil Stanek");
        Max.enrollInCourse(ELA101);
        Phil.enrollInCourse(ELA101);
        John.assignQuiz(ELA101, quiz, Max);
        John.assignQuiz(ELA101, quiz, Phil);
    }

    @Test
    public void test_answerAllQuestionsCorrectly()
    {
        QuizAttempt quizAttempt = Max.startQuiz(quiz);
        for (int i=0; i < quiz.getNumberOfQuestions(); i++)
        {
            MultipleChoiceQuestion question = quiz.getQuestions().get(i);
            Max.answerQuizQuestion(quizAttempt, question, CORRECT_ANSWERS[i]);
        }
        Max.submitQuiz(quiz);
        int quizGrade = Max.getQuizGradeForQuiz(quiz);
        Assert.assertEquals(3, quizGrade);
    }

    @Test
    public void test_answerFirst2QuestionsOnly()
    {
        QuizAttempt quizAttempt = Max.startQuiz(quiz);
        for (int i=0; i < 2; i++)
        {
            MultipleChoiceQuestion question = quiz.getQuestions().get(i);
            Max.answerQuizQuestion(quizAttempt, question, CORRECT_ANSWERS[i]);
        }
        Max.submitQuiz(quiz);
        int quizGrade = Max.getQuizGradeForQuiz(quiz);
        Assert.assertEquals(2, quizGrade);
    }

    @Test
    public void test_didNotAnswerAnyQuestion()
    {
        QuizAttempt quizAttempt = Max.startQuiz(quiz);
        Max.submitQuiz(quiz);
        int quizGrade = Max.getQuizGradeForQuiz(quiz);
        Assert.assertEquals(0, quizGrade);
    }

    @Test
    public void test_hasNotSubmittedTheTestAndGradeIsRecorded()
    {
        QuizAttempt quizAttempt = Max.startQuiz(quiz);
        for (int i=0; i < 2; i++)
        {
            MultipleChoiceQuestion question = quiz.getQuestions().get(i);
            Max.answerQuizQuestion(quizAttempt, question, CORRECT_ANSWERS[i]);
        }
        int quizGrade = Max.getQuizGradeForQuiz(quiz);
        Assert.assertEquals(2, quizGrade);
    }

    @Test(expected = ForbiddenException.class)
    public void test_cannotStartQuizAgainAfterSubmitted()
    {
        Max.startQuiz(quiz);
        Max.submitQuiz(quiz);
        Max.startQuiz(quiz);
    }
}
