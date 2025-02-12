package com.quizapp.service;

import com.quizapp.model.QuestionCategory;
import com.quizapp.repository.QuestionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionCategoryService {

    private final QuestionCategoryRepository questionCategoryRepository;

    @Autowired
    public QuestionCategoryService(QuestionCategoryRepository questionCategoryRepository) {
        this.questionCategoryRepository = questionCategoryRepository;
    }

    // 获取所有类别
    public List<QuestionCategory> getAllCategories() {
        return questionCategoryRepository.findAll();
    }

    // 通过ID获取类别
    public QuestionCategory getCategoryById(int id) {
        return questionCategoryRepository.findById(id).orElse(null);
    }
}
