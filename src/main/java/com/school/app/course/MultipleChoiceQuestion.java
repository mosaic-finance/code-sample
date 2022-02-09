package com.school.app.course;

import java.util.List;

public class MultipleChoiceQuestion extends Question
{
    private List<String> choices;
    private String question;

    public MultipleChoiceQuestion(String question, List<String> choices, String correctAnswer)
    {
        super(question, correctAnswer);
        this.choices = choices;
    }
}
