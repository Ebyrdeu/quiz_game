package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz", schema = "quiz_game_db")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizId")
    private int quizId;

    @ManyToOne
    @JoinColumn(name = "difficultyId", nullable = false)
    private Difficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @Column(name = "quizQuestion", nullable = false)
    private String quizQuestion;

    @Column(name = "correctAnswer", nullable = false)
    private String correctAnswer;

    public int id() {
        return quizId;
    }

    public Quiz setId(int quizId) {
        this.quizId = quizId;
        return this;
    }

    public Difficulty difficulty() {
        return difficulty;
    }

    public Quiz setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Category category() {
        return category;
    }

    public Quiz setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String quizQuestion() {
        return quizQuestion;
    }

    public Quiz setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
        return this;
    }

    public String correctAnswer() {
        return correctAnswer;
    }

    public Quiz setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }
}