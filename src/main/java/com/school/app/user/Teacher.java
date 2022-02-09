package com.school.app.user;

import java.util.ArrayList;
import java.util.List;

import com.school.app.course.Course;
import com.school.app.course.Quiz;
import com.school.app.exception.ForbiddenException;

public class Teacher extends Person
{
    private List<Course> myCourses;

    public Teacher(String fullName)
    {
        super(fullName);
        myCourses = new ArrayList<Course>();
    }

    public void addToMyCourseList(Course course)
    {
        myCourses.add(course);
    }

    public Quiz createQuiz(Course course, String quizName)
    {
        if (course.getTeacher() == this)
        {
            for (Quiz quiz : course.getQuizListForCourse())
            {
                if (quiz.getQuizName() == quizName)
                {
                    throw new ForbiddenException("this quiz name already exists");
                }
            }
            Quiz quiz = new Quiz(quizName, course);
            course.addQuiz(quiz);
            return quiz;
        }
        else
        {
            throw new ForbiddenException("teacher " + this.getName() + " isn't the owner of the course '" + course.getCourseName() + "'");
        }
    }

    public void assignQuiz(Course course, Quiz quiz, Student student)
    {
        if (course.getTeacher() == this)
        {
            student.receiveQuizAssignment(course, quiz);
        }
        else
        {
            throw new ForbiddenException("teacher " + this.getName() + " isn't the owner of the course '" + course.getCourseName() + "'");
        }
    }

    public int calculateGradesForStudent(Student student)
    {
        System.out.println("###### student: ###### "+student.getName());
        int totalGradeForAllCourses = 0;
        for (Course course : myCourses)
        {
            System.out.println("******* course: ******** "+ course.getCourseName());
            for (Student enrolledStudent : course.getStudentList())
            {
                if (enrolledStudent == student)
                {
                    int studentGradeForCourse = calculateStudentGradeForOneCourse(student, course);
                    totalGradeForAllCourses = totalGradeForAllCourses + studentGradeForCourse;
                }
            }
        }
        System.out.println("Total grade for student " + student.getName() + " for all courses: " + totalGradeForAllCourses);
        return totalGradeForAllCourses;
    }

    public int calculateStudentGradeForOneCourse(Student student, Course course)
    {
        if (student.isEnrolledInCourse(course))
        {
            int totalGradeForStudentAndCourse = 0;
            for (Quiz quiz : course.getQuizListForCourse())
            {
                System.out.println("studentGradeForQuiz: " + quiz.getQuizName() + " is: " + student.getQuizGradeForQuiz(quiz));
                totalGradeForStudentAndCourse = totalGradeForStudentAndCourse + student.getQuizGradeForQuiz(quiz);
            }
            System.out.println("final grade for student " +
                               student.getName() +
                               " for course '" +
                               course.getCourseName() +
                               "' is: " +
                               totalGradeForStudentAndCourse);
            return totalGradeForStudentAndCourse;
        }
        else
        {
            throw new ForbiddenException("the sudent " + student.getName() + " is not enrolled in this course");
        }
    }
}
