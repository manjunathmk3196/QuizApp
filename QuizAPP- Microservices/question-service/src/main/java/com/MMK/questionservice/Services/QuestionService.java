package com.MMK.questionservice.Services;

import com.MMK.questionservice.DAO.QuestionDAO;
import com.MMK.questionservice.Models.Question;
import com.MMK.questionservice.Models.QuestionWrapper;
import com.MMK.questionservice.Models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ResponseEntity<List<Integer>> getQuestionforQuiz(String category, Integer numQuestions) {
        List<Integer> questions = questionDAO.findRandomQuestionsByCategory(category, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for (Integer id : questionIds) {
            questions.add(questionDAO.findById( Long.valueOf(id)).get());
        }
        for (Question question : questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());

            wrappers.add(wrapper);

        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;
        for(Response response:responses){
            Question question=questionDAO.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
                right++;
        }
return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
