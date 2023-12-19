CREATE DATABASE projektGruppJava;

CREATE TABLE difficulty
(
    difficultyId   INT PRIMARY KEY,
    difficultyType VARCHAR(255) NOT NULL
);

CREATE TABLE category
(
    categoryId   INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    categoryName VARCHAR(255) NOT NULL
);



CREATE TABLE student
(
    studentId INT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(255) NOT NULL,
    age       INT,
    email     VARCHAR(255),
    class     VARCHAR(50)
);

CREATE TABLE quiz
(
    quizId        INT PRIMARY KEY AUTO_INCREMENT,
    difficultyId  INT,
    categoryId    INT,
    quizQuestion  VARCHAR(255) NOT NULL,
    correctAnswer VARCHAR(255) NOT NULL,
    FOREIGN KEY (difficultyId) REFERENCES difficulty (difficultyId),
    FOREIGN KEY (categoryId) REFERENCES category (categoryId)
);

CREATE TABLE result
(
    resultId        INT PRIMARY KEY AUTO_INCREMENT,
    resultStudentId INT,
    score           INT,
    FOREIGN KEY (resultStudentId) REFERENCES student (studentId)
);

CREATE UNIQUE INDEX index_student_email ON student (email);
CREATE UNIQUE INDEX index_category_categoryName ON category (categoryName);
CREATE UNIQUE INDEX index_difficulty_difficultyType ON difficulty (difficultyType);


