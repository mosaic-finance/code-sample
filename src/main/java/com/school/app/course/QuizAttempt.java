package com.school.app.course;

import java.util.ArrayList;
import java.util.List;

import com.school.app.user.Student;

public class QuizAttempt
{
    private Student student;
    private Quiz quiz;
    private List<String> studentAnswers;
    private int studentGradeForQuiz = 0;

    // I assumed quizzes are not resumable. Even if they are not fully taken, an incomplete submission is a final submission.
    // Also, I assumed that student only have the right to have one attempt for a quiz
    // I assume that if the student's browser crashes or the student leaves the quiz without submitting it, all his answers are lost
    // I assume the student is not allowed to go back to change a previous answer, but can only go forward in the quiz.
    // Finally, I assume the questions are worth 1 point and the answer is either right or wrong,
    // meaning grade for question is either 1 or 0
    public QuizAttempt(Student student, Quiz quiz)
    {
        this.student = student;
        this.quiz = quiz;
        // saving answers for possible review of test by student of instructor
        studentAnswers = new ArrayList<String>();
    }

    public void addAnswer(String answer)
    {
        studentAnswers.add(answer);
    }

    public Quiz getQuiz()
    {
        return quiz;
    }

    public int getStudentGradeForQuiz()
    {
        return studentGradeForQuiz;
    }

    public void incrementStudentGradeForQuiz()
    {
        studentGradeForQuiz++;
    }
}
