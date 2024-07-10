package com.learn.Quiz_Service.model;

import lombok.Data;

@Data
public class QuizDto {
    private String category;
    private Integer numofQ;
    private String quiztitle;
}
