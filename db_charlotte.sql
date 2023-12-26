CREATE DATABASE IF NOT EXISTS quiz_game_db;

CREATE TABLE difficulty
(
    difficultyId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
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


-- difficulty easy medium hard
INSERT INTO difficulty (difficultyId, difficultyType)
VALUES (1, 'easy'),
       (2, 'medium'),
       (3, 'hard');

-- sample data category
INSERT INTO category (categoryName)
VALUES ('Science'),
       ('History'),
       ('Geography');

-- sample data quiz frågor
INSERT INTO quiz (difficultyId, categoryId, quizQuestion, correctAnswer)
VALUES (1, 1, 'What is the solar system’s biggest star?', 'The sun'),
       (2, 1, 'Which illness was the first to get a vaccine?', 'Smallpox'),
       (3, 1, 'In 1774, Joseph Priestley discovered what element? ?', 'Oxygen'),
       (1, 2, 'Who was the Greek God of War?', 'Ares'),
       (2, 2, 'On what date was the Declaration of Independence signed?', 'August 2nd, 1776'),
       (3, 2, 'On what island was Napoleon born?', 'Corsica'),
       (1, 3, 'What is the capital of Ireland?', 'Dublin'),
       (2, 3, 'On what continent would you find the world’s largest desert?', 'Antarctica'),
       (3, 3, 'What is the only flag that does not have four sides', 'Nepal');

-- sample date students
INSERT INTO student (name, age, email, class)
VALUES ('Will Ferrel', 18, 'Will.Ferrel@hotmail.com', 'ClassA'),
       ('Taylor Swift', 18, 'Taylor.Swift@hotmail.com', 'ClassA'),
       ('Elton John', 19, 'Elton.John@hotmail.com', 'ClassB'),
       ('Emma Watson', 19, 'Emma.Watson@hotmail.com', 'ClassB'),
       ('Jennifer Lawrence', 20, 'Jennifer.Lawrence@hotmail.com', 'ClassC'),
       ('Beyonce Knowles', 20, 'Beyonce.Knowles@hotmail.com', 'ClassC'),
       ('George Clooney', 18, 'George.Clooney@hotmail.com', 'ClassA');

-- sample date för score
INSERT INTO result (resultStudentId, score)
VALUES (1, 61),
       (2, 93),
       (3, 73),
       (4, 92),
       (5, 68),
       (6, 52),
       (7, 78);

