package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz", schema = "projektGruppJava")
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

    public int getQuizId() {
        return quizId;
    }

    public Quiz setQuizId(int quizId) {
        this.quizId = quizId;
        return this;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Category getCategory() {
        return category;
    }

    public Quiz setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public Quiz setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
        return this;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public Quiz setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }
}