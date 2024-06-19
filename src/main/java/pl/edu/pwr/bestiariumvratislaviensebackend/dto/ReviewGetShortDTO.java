package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Review;

import java.time.Instant;

@Data
public class ReviewGetShortDTO {
    private String story_title;
    private Instant date;

    public ReviewGetShortDTO(Review review) {
        story_title = review.getStory().getTitle();
        date = review.getCreated();
    }
}
