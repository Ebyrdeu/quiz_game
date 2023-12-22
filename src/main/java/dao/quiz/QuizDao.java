package dao.quiz;

import dao.Dao;
import entity.Quiz;

import java.util.List;

public interface QuizDao extends Dao<Quiz> {
    List<Quiz> readAll();
}