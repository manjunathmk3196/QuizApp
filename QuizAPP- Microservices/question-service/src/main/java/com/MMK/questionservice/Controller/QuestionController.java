package com.MMK.questionservice.Controller;

import com.MMK.questionservice.Models.Question;
import com.MMK.questionservice.Models.QuestionWrapper;
import com.MMK.questionservice.Models.Response;
import com.MMK.questionservice.Services.QuestionService;
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
        return new ResponseEntity<>(questionService.addQuestions(question), HttpStatus.CREATED);
    }

    ////Microservice started from here
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions) {
        return questionService.getQuestionforQuiz(category, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }
}
