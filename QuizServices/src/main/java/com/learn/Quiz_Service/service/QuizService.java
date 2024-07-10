package com.learn.Quiz_Service.service;


import com.learn.Quiz_Service.feign.QuizInterface;
import com.learn.Quiz_Service.model.QuestionWrapper;
import com.learn.Quiz_Service.model.Quiz;
import com.learn.Quiz_Service.model.Response;
import com.learn.Quiz_Service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, Integer numofQ, String quiztitle) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numofQ).getBody();
        System.out.println(questions);
        Quiz quiz = new Quiz();
        quiz.setQuizTitle(quiztitle);
        quiz.setQuestionid(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(Integer quizId) {

        Quiz quiz = quizRepository.findById(quizId).get();
        List<Integer> questionId = quiz.getQuestionid();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionFromId(questionId);

        return questions;

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }

}

