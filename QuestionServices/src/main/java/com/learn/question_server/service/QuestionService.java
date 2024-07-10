package com.learn.question_server.service;



import com.learn.question_server.model.QuestionWrapper;
import com.learn.question_server.model.Response;
import com.learn.question_server.model.question;
import com.learn.question_server.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestion) {

        List<Integer>questions=questionRepository.findRandomQuestionsByCategory(categoryName,numQuestion);
        System.out.println(questions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper>wrappers=new ArrayList<>();
        List<question>questions=new ArrayList<>();

        for(Integer id:questionIds){
            questions.add(questionRepository.findById(id).get());
        }

        for(question q:questions){
            QuestionWrapper wrapper=new QuestionWrapper();
            wrapper.setId(q.getId());
            wrapper.setQuestionTitle(q.getQuestionTitle());
            wrapper.setOption1(q.getOption1());
            wrapper.setOption2(q.getOption2());
            wrapper.setOption3(q.getOption3());
            wrapper.setOption4(q.getOption4());
            wrappers.add(wrapper);

        }


        return new ResponseEntity<>(wrappers,HttpStatus.OK);

    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right=0;

        for(Response response:responses) {

            if (response == null || response.getResponse() == null) {
                continue;
            }
            question ques=questionRepository.findById(response.getId()).get();
            if (response.getResponse().equals(ques.getAnswer())) {
                right++;
            }

        }
        return new ResponseEntity<>(right,HttpStatus.OK);

        }

//         for (Response response : responses) {
//
//
//        Optional<Question> optionalQuestion = questionRepository.findById(response.getId());
//        if (optionalQuestion.isPresent()) {
//            Question ques = optionalQuestion.get();
//            if (Objects.equals(response.getResponse(), ques.getAnswer())) {
//                right++;
//            }
//        }

    }

