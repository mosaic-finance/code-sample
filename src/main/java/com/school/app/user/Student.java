package com.school.app.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.school.app.course.Course;
import com.school.app.course.MultipleChoiceQuestion;
import com.school.app.course.Quiz;
import com.school.app.course.QuizAttempt;
import com.school.app.exception.ForbiddenException;

public class Student extends Person
{
    private List<Course> courses;
    private List<Quiz> availableQuizzes;
    private HashMap<Quiz,Integer> quizGrades;
    private QuizAttempt currentQuizAttempt;

    public Student(String fullName)
    {
        super(fullName);
        courses = new ArrayList<Course>();
        availableQuizzes = new ArrayList<Quiz>();
        quizGrades = new HashMap<Quiz,Integer>();
    }

    public void enrollInCourse(Course course)
    {
        courses.add(course);
        course.addStudent(this);
    }

    public Boolean isEnrolledInCourse(Course newCourse)
    {
        if (courses.size() == 0)
        {
            return false;
        }
        for (Course course: courses)
        {
            if (course == newCourse)
            {
                return true;
            }
        }
        return false;
    }

    public void receiveQuizAssignment(Course course, Quiz quiz)
    {
        if (this.isEnrolledInCourse(course))
        {
            availableQuizzes.add(quiz);
        }
        else
        {
            throw new ForbiddenException("student " + this.getName() + " isn't enrolled in the course '" + course.getCourseName() + "'");
        }
    }

    public Boolean canTakeQuiz(Quiz newQuiz)
    {
        if (availableQuizzes.size() == 0)
        {
            return false;
        }
        for (Quiz quiz : availableQuizzes)
        {
            if (quiz == newQuiz)
            {
                return true;
            }
        }
        return false;
    }

    public QuizAttempt startQuiz(Quiz quiz)
    {
        if (this.canTakeQuiz(quiz))
        {
            if (!quizGrades.containsKey(quiz))
            {
                // initialize grade for quiz
                quizGrades.put(quiz, 0);
                currentQuizAttempt = new QuizAttempt(this, quiz);
            }
            return currentQuizAttempt;
        }
        else
        {
            throw new ForbiddenException("student " + this.getName() + " has not been assigned quiz '"
                                         + quiz.getQuizName() + "' or has already submitted it");
        }
    }

    public void updateQuizGrade(Quiz quiz, int newGrade)
    {
        quizGrades.put(quiz, newGrade);
    }

    public void submitQuiz(Quiz quiz)
    {
        availableQuizzes.remove(quiz);
    }


    public void answerQuizQuestion(QuizAttempt quizAttempt, MultipleChoiceQuestion question, String studentAnswer)
    {
        currentQuizAttempt.addAnswer(studentAnswer);
        if (studentAnswer == question.getCorrectAnswer())
        {
            currentQuizAttempt.incrementStudentGradeForQuiz();
            updateQuizGrade( currentQuizAttempt.getQuiz(), currentQuizAttempt.getStudentGradeForQuiz());
        }
    }

    public int getQuizGradeForQuiz(Quiz quiz)
    {
        return quizGrades.get(quiz);
    }
}
