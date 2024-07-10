package com.learn.quizApp.service;

import com.learn.quizApp.model.question;
import com.learn.quizApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public question createQuestion(question questionInput) {
        return questionRepository.save(questionInput);
    }

    public List<question> getByCategory(String category){
        return questionRepository.findQuestionsByCategory(category);
    }
}
