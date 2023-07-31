package com.MMK.quizService.Services;


import com.MMK.quizService.DAO.QuizDao;
import com.MMK.quizService.Feign.FeignQuizInterface;
import com.MMK.quizService.Models.Question;
import com.MMK.quizService.Models.QuestionWrapper;
import com.MMK.quizService.Models.Quiz;
import com.MMK.quizService.Models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    FeignQuizInterface feignQuizInterface;

//    @Autowired
//    QuestionDAO questionDAO;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = feignQuizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Successfully added", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
    Quiz quiz=quizDao.findById(id).get();
    List<Integer> questionids=quiz.getQuestions();
    ResponseEntity<List<QuestionWrapper>> questions=feignQuizInterface.getQuestionsFromId(questionids);
    return questions;

    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> scores=feignQuizInterface.getScore(responses);
        return scores;

    }
}
