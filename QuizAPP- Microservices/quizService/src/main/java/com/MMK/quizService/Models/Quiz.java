package com.MMK.quizService.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    //    @ManyToMany
//    private List<Question> questions;
    @ElementCollection
    private List<Integer> questions;
}
