package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Review;

import java.time.Instant;

@Data
public class ReviewGetDTO {
    private String author;
    private Instant date;
    private Double rating;
    private String body;

    ReviewGetDTO(Review review) {
        author = review.getAuthor().getUsername();
        date = review.getCreated();
        rating = review.getRating();
        body = review.getBody();
    }
}
