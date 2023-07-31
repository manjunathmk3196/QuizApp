package com.MMK.quizService.Controller;

import com.MMK.quizService.DTO.QuizDTO;
import com.MMK.quizService.Models.QuestionWrapper;
import com.MMK.quizService.Models.Response;
import com.MMK.quizService.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        return quizService.createQuiz(quizDTO.getCategoryName(), quizDTO.getNumQuestions(),quizDTO.getTitle());
    }
@GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
    return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }
}
