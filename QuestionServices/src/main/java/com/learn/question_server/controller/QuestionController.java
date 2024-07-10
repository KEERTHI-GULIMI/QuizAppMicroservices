package com.learn.question_server.controller;


import com.learn.question_server.model.QuestionWrapper;
import com.learn.question_server.model.Response;
import com.learn.question_server.model.question;
import com.learn.question_server.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {


    @Autowired
    QuestionService questionService;

    @GetMapping("/questions")
    public List<question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/questions")
    public question createQuestion(@RequestBody question questionInput) {
        return questionService.createQuestion(questionInput);
    }

    @GetMapping("/questions/category/{category}")
    public List<question>getByCategory(@PathVariable String category){
        return questionService.getByCategory(category);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>>getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestion){

        return questionService.getQuestionsForQuiz(categoryName,numQuestion);
    }

    @PostMapping("/getquestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestionFromId(@RequestBody List<Integer> questionIds){

        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer>getScore(@RequestBody List<Response> responses){

        return questionService.getScore(responses);
    }



}
