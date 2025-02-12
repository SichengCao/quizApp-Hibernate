package com.quizapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz_question")  // 关联 `quiz_question` 表
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qq_id")
    private int id;

    @Column(name = "quiz_id")
    private int quizId;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "option_a")
    private String optionA;

    @Column(name = "option_b")
    private String optionB;

    @Column(name = "option_c")
    private String optionC;

    @Column(name = "option_d")
    private String optionD;

    @Column(name = "correct_option")
    private String correctOption;

    // 关联 `question` 表，获取 `category_id`
    @OneToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private QuestionCategory category;

    public Question() {}

    public Question(int id, int quizId, String questionText, String optionA, String optionB, String optionC, String optionD, String correctOption, QuestionCategory category) {
        this.id = id;
        this.quizId = quizId;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
        this.category = category;
    }

    public Question(int questionId, int quizId, String questionText, String optionA, String optionB, String optionC, String optionD, String correctOption) {
        this.id = questionId;  // 这里用 `id` 代替 `questionId`，确保一致性
        this.quizId = quizId;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    // ✅ 增加 `questionId` 获取方法（从 `category` 获取）
    public int getQuestionId() {
        return category != null ? category.getQuestionId() : id;  // 避免空指针异常，优先使用 category
    }


    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuizId() { return quizId; }
    public void setQuizId(int quizId) { this.quizId = quizId; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getOptionD() { return optionD; }
    public void setOptionD(String optionD) { this.optionD = optionD; }

    public String getCorrectOption() { return correctOption; }
    public void setCorrectOption(String correctOption) { this.correctOption = correctOption; }

    public QuestionCategory getCategory() { return category; }
    public void setCategory(QuestionCategory category) { this.category = category; }
}
