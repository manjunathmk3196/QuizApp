package com.example.QuizApp.Services;

import com.example.QuizApp.DAO.QuestionDAO;
import com.example.QuizApp.Models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    public List<Question> getAllQuestions() {
        return questionDAO.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return questionDAO.findByCategory(category);
    }

    public String addQuestions(Question question) {
        questionDAO.save(question);
        return "Succesfully added questions";
    }
}
