package com.MMK.quizService.DAO;


import com.MMK.quizService.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
