package com.learn.quizApp.repository;

import com.learn.quizApp.model.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<question, Integer> {

    List<question> findQuestionsByCategory(String category);

    @Query(value="SELECT *FROM question q where q.category=:category ORDER BY RANDOM() LIMIT :numofQ",nativeQuery=true)
    List<question> findRandomQuestionsByCategory(String category, int numofQ);
}
