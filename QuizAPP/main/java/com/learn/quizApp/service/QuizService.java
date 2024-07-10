package com.learn.quizApp.service;

import com.learn.quizApp.model.QuestionWrapper;
import com.learn.quizApp.model.Quiz;
import com.learn.quizApp.model.question;
import com.learn.quizApp.repository.QuestionRepository;
import com.learn.quizApp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numofQ, String quiztitle) {
        Quiz quiz = new Quiz();
        List<question> questionsFromDb = questionRepository.findRandomQuestionsByCategory(category, numofQ);
        quiz.setQuizTitle(quiztitle);
        quiz.setQuestions(questionsFromDb);
        quizRepository.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);

    }

//    public ResponseEntity<List<QuestionWrapper>> getQuizById(Integer quizId) {
//
//        Optional<Quiz> quiz =quizRepository.findById(quizId);
//        List<question> quesFromDb = quiz.get().getQuestions();
//        List<QuestionWrapper> quesForUsers = new ArrayList<>();
//
//        for (question q : quesFromDb) {
//            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
//            quesForUsers.add(qw);
//        }
//
//        return new ResponseEntity<>(quesForUsers, HttpStatus.OK);
//
//    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable Integer quizId) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);

        if (optionalQuiz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Quiz quiz = optionalQuiz.get();
        List<question> quesFromDb = quiz.getQuestions();
        List<QuestionWrapper> quesForUsers = new ArrayList<>();

        for (question q : quesFromDb) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            quesForUsers.add(qw);
        }

        return new ResponseEntity<>(quesForUsers, HttpStatus.OK);
    }
}

