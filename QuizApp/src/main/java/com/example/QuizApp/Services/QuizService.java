package com.example.QuizApp.Services;

import com.example.QuizApp.DAO.QuestionDAO;
import com.example.QuizApp.DAO.QuizDao;
import com.example.QuizApp.Models.Question;
import com.example.QuizApp.Models.QuestionWrapper;
import com.example.QuizApp.Models.Quiz;
import com.example.QuizApp.Models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDAO questionDAO;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions= questionDAO.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Successfully added",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionsFromDB=quiz.get().getQuestions();
        //we need to add the data for user
        List<QuestionWrapper> questionForUser=new ArrayList<>();
//adding the data for user
        for(Question q:questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }
        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=0;

        for (Response response: responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
