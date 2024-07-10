package com.learn.quizApp.controller;

import com.learn.quizApp.model.QuestionWrapper;
import com.learn.quizApp.service.QuizService;
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
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numofQ, @RequestParam String Quiztitle) {
        return quizService.createQuiz(category, numofQ, Quiztitle);

    }

    @GetMapping("/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer quizId) {

        return quizService.getQuizById(quizId);
    }


}
