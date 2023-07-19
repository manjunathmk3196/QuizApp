package com.example.QuizApp.Controller;

import com.example.QuizApp.Models.Question;
import com.example.QuizApp.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("Question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    //    @GetMapping("allQuestions")
//    public List<Question> getAllQuestions() {
//        return questionService.getAllQuestions();
//    }
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("category/{category}")
    public <category> List<Question> getAllQuestionsByCategory(@PathVariable String category) {
        return questionService.getAllQuestionsByCategory(category);
    }

//    @PostMapping("addQuestion")
//    public String addQuestions(@RequestBody Question question) {
//        return questionService.addQuestions(question);
//    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestions(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestions(question),HttpStatus.CREATED);
    }
}
