package com.example.QuizApp.DAO;

import com.example.QuizApp.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
