package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "result", schema = "quiz_game_db")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resultId")
    private int id;


    @ManyToOne
    @JoinColumn(name = "resultStudentId", nullable = false)
    private Student student;

    @Column(name = "score")
    private int score;

    public int id() {
        return id;
    }

    public Student student() {
        return student;
    }

    public int score() {
        return score;
    }

    public Result setId(int id) {
        this.id = id;
        return this;
    }

    public Result setStudent(Student student) {
        this.student = student;
        return this;
    }

    public Result setScore(int score) {
        this.score = score;
        return this;
    }
}
