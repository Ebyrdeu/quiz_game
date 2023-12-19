CREATE DATABASE Quizdb;

USE Quizdb;

CREATE TABLE Student
(
    studentID BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    age       INT,
    class     VARCHAR(255)
);

CREATE TABLE Category
(
    categoryID BIGINT AUTO_INCREMENT PRIMARY KEY,
    category   VARCHAR(255) NOT NULL
);

CREATE TABLE Difficulty
(
    difficultyID BIGINT AUTO_INCREMENT PRIMARY KEY,
    level        VARCHAR(255) NOT NULL
);

CREATE TABLE Quiz
(
    quizID       BIGINT AUTO_INCREMENT PRIMARY KEY,
    quizQuestion VARCHAR(255) NOT NULL,
    categoryID   BIGINT,
    difficultyID BIGINT,
    FOREIGN KEY (categoryID) REFERENCES Category (categoryID),
    FOREIGN KEY (difficultyID) REFERENCES Difficulty (difficultyID)
);

CREATE TABLE Result
(
    resultID  BIGINT AUTO_INCREMENT PRIMARY KEY,
    point     INT NOT NULL,
    studentID BIGINT,
    FOREIGN KEY (studentID) REFERENCES Student (studentID)
);
