package com.MMK.questionservice.DAO;


import com.MMK.questionservice.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Long> {
    List<Question> findByCategory(String category);
    //   List<Question> findRandomQuestionsByCategory(String category, int numQ);
    @Query(value = "Select q.id from question q where q.category=:category ORDER BY RANDOM() LIMIT :numQuestions",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category,Integer numQuestions);

}
