package com.MMK.quizService.Feign;

import com.MMK.quizService.Models.QuestionWrapper;
import com.MMK.quizService.Models.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface FeignQuizInterface {
    @GetMapping("Question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions);

    @PostMapping("Question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("Question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
