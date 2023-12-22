package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category", schema = "projektGruppJava")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private int categoryId;


    @Column(name = "categoryName")
    private String name;


    public int categoryId() {
        return categoryId;
    }

    public Category setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String name() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

}
