package com.example.QuizApp.DAO;

import com.example.QuizApp.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Long> {
    List<Question> findByCategory(String category);
//   List<Question> findRandomQuestionsByCategory(String category, int numQ);
    @Query(value = "Select * from question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);

}
