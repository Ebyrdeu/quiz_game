package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category", schema = "ProjektGruppJava")
public class Category {

    @Id
    @Column(name = "categoryId")
    private int categoryId;


    @Column(name = "categoryName")
    private String name;


    public int categoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String name() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

}
