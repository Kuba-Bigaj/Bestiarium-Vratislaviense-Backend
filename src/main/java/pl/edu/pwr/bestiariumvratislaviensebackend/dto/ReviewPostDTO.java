package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

@Data
public class ReviewPostDTO {
    private Long authorID;
    private Long storyID;
    private Double rating;
    private String body;
}
