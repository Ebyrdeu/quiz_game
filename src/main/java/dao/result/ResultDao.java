package dao.result;

import dao.Dao;
import entity.Result;

import java.util.List;

public interface ResultDao extends Dao<Result> {

    List<Result> readAllLeaderBoard();
}
