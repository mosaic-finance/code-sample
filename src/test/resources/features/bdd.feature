Given "John Doe" is a teacher
When he is teaching the class "Finetune Learning"
Then the following students can enroll in the class
| Lea Monroe   |
| Rebecca Fitz |
| Greg Simpson |

Given "John Doe" is an instructor for the class "Finetune Learning"
When the instructor enters the class "Finetune 101"
Then the instructor can create "5" quizzes.

Given "John Doe" is an instructor for the class "Finetune 101"
When the instructor opens the quiz "Finetune 101 Quiz 1"
Then the instructor can add "5" multiple choice questions

Given "John Doe" has created a quiz called "Finetune 101 First Quiz"
When "Lea Monroe" is enrolled in the class "Finetune Learning"
Then the instructor can assign the quiz to the student

Given "Lea Monroe" is assigned quiz "Finetune 101 First Quiz"
When the student answers "<numberOfQuestions>" questions of the quiz
Then the student can submit her answers
| numberOfQuestions |
| 0                 |
| 1                 |
| all               |

Given "Lea Monroe" has answered "<numberOfQuestions>" questions for quiz "Finetune 101 First Quiz"
When the student submits the quiz
Then the student can get her grade for the quiz
| numberOfQuestions |
| 0                 |
| 1                 |
| all               |

Given "Lea Monroe" is a student of "John Doe"'s' "Finetune Learning" class
When she has taken "<numberOfQuizzes>" quizzes in the class
Then "John Doe" can get the student's total grade for the class
| numberOfQuizzes |
| 0               |
| 1               |
| all             |

Given "Lea Monroe" has enrolled in "2" of "John Doe"'s classes
When the student has submitted all of the quizzes for the classes
Then the instructor can get the total grade for the student all the classes for the semester
