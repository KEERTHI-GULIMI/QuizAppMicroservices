package com.learn.Quiz_Service.controller;


import com.learn.Quiz_Service.model.QuestionWrapper;
import com.learn.Quiz_Service.model.QuizDto;
import com.learn.Quiz_Service.model.Response;
import com.learn.Quiz_Service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.getCategory(),quizDto.getNumofQ(),quizDto.getQuiztitle());

    }

    @GetMapping("/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer quizId) {

        return quizService.getQuizById(quizId);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

}
