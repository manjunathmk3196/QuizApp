package com.example.QuizApp.Models;

import lombok.Data;

@Data
public class QuestionWrapper { // instead of Question CLASS WE R USING QuestionWrapper bcz of avoiding the some fields(ex:rightAnswer) etc
    private Long id;
    private String questionTitle;
    private String option1;
    private String option2;

    private String option3;
    private String option4;
    public QuestionWrapper(Long id, String questionTitle, String option1, String option2, String option3, String option4) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

}
