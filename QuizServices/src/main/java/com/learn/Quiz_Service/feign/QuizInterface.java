package com.learn.Quiz_Service.feign;

import com.learn.Quiz_Service.model.QuestionWrapper;
import com.learn.Quiz_Service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@FeignClient("QUESTION-SERVER")
public interface QuizInterface {

    @GetMapping("/api/generate")
      ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestion);

    @PostMapping("/api/getquestions")
     ResponseEntity<List<QuestionWrapper>>getQuestionFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("/api/getScore")
     ResponseEntity<Integer>getScore(@RequestBody List<Response> responses);


}
