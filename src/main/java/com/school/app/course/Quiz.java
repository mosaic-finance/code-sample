package com.school.app.course;

import java.util.ArrayList;
import java.util.List;

import com.school.app.user.Student;

public class Quiz
{
    private String quizName;
    private int numberOfQuestions;
    private List<MultipleChoiceQuestion> questions;
    private List<Student> allowedStudents;
    private Course course;

    public Quiz(String quizName, Course course)
    {
        this.quizName = quizName;
        this.course = course;
        questions = new ArrayList<MultipleChoiceQuestion>();
    }

    public void addQuestion(MultipleChoiceQuestion question)
    {
        questions.add(question);
        numberOfQuestions ++;
    }

    public Course getCourse()
    {
        return course;
    }

    public int getNumberOfQuestions()
    {
        return numberOfQuestions;
    }

    public String getQuizName()
    {
        return quizName;
    }

    public List<MultipleChoiceQuestion> getQuestions()
    {
        return questions;
    }
}

