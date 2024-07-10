package com.learn.question_server.repository;



import com.learn.question_server.model.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<question, Integer> {

    List<question>findQuestionsByCategory(String category);

    @Query(value= "SELECT q.id FROM question q WHERE q.category=:categoryName ORDER BY RANDOM() LIMIT :numQuestion", nativeQuery=true)

    List<Integer> findRandomQuestionsByCategory(@Param("categoryName") String categoryName, @Param("numQuestion") Integer numQuestion);

}
