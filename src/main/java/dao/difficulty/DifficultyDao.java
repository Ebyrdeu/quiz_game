package dao.difficulty;

import entity.Difficulty;

import java.util.List;

public interface DifficultyDao {
    void insertDifficulty(Difficulty difficulty);

    Difficulty findDifficultyById(Long id);

    List<Difficulty> findAllDifficulties();

    void updateDifficulty(Difficulty difficulty);

    void deleteDifficulty(Long id);
}
