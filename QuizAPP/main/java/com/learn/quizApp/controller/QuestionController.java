package com.learn.quizApp.controller;

import com.learn.quizApp.model.question;
import com.learn.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {


    @Autowired
    QuestionService questionService;

    @GetMapping("/allquestions")
    public List<question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/questions")
    public question createQuestion(@RequestBody question questionInput) {
        return questionService.createQuestion(questionInput);
    }

    @GetMapping("questions/category/{category}")
    public List<question>getByCategory(@PathVariable String category){
        return questionService.getByCategory(category);
    }

}
