package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "difficulty", schema = "projektGruppJava")
public class Difficulty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "difficultyId")
    private int id;

    @Column(name = "difficultyType")
    private String name;

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Difficulty setId(int id) {
        this.id = id;
        return this;
    }

    public Difficulty setName(String name) {
        this.name = name;
        return this;
    }
}