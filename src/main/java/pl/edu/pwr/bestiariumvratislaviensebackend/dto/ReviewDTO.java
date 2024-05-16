package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Integer authorID;
    private Double rating;
    private String body;
}
