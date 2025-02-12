package com.quizapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "question")  // 关联 `question` 表
public class QuestionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int id;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "description")
    private String description;

    public QuestionCategory() {}

    public QuestionCategory(int id, int categoryId, String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.description = description;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getQuestionId() {
        return id;
    }
}
