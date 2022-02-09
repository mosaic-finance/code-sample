package com.school.app.course;

import java.util.ArrayList;
import java.util.List;

import com.school.app.exception.ForbiddenException;
import com.school.app.user.Teacher;
import com.school.app.user.Student;

public class Course
{
    private Teacher teacher;
    private List<Student> students;
    private List<Quiz> quizzes;
    private int maxStudents;
    private String courseName;
    
    public Course(int maxStudents, Teacher teacher, String courseName)
    {
        students = new ArrayList<Student>();
        this.maxStudents = maxStudents;
        this.teacher = teacher;
        teacher.addToMyCourseList(this);
        this.courseName = courseName;
        quizzes = new ArrayList<Quiz>();

    }

    public void addStudent(Student student) {
        if (isFull())
        {
            throw new ForbiddenException("there is no more space in course '" + courseName + "'");
        }
        else
        {
            students.add(student);
        }
    }


    public String getCourseName()
    {
        return courseName;
    }

    public Teacher getTeacher()
    {
        return teacher;
    }

    public void addQuiz(Quiz quiz)
    {
        quizzes.add(quiz);
    }

    public Boolean hasQuiz(Quiz newQuiz)
    {
        if (quizzes.size() == 0)
        {
            return false;
        }
        for (Quiz quiz : quizzes)
        {
            if (quiz == newQuiz)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isFull()
    {
        Boolean full = false;
        if (students.size() == maxStudents)
        {
            full = true;
        }
        return full;
    }

    public Boolean isEnrolledInClass(Student newStudent)
    {
        for (Student student : students)
        {
            if (student == newStudent)
            {
                return true;
            }
        }
        return false;
    }

    public List<Quiz> getQuizListForCourse()
    {
        return quizzes;
    }

    public List<Student> getStudentList()
    {
        return students;
    }

}
