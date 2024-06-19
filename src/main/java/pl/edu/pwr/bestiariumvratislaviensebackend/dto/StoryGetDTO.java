package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Review;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Story;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Tag;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class StoryGetDTO {
    private Long id;
    private String title;
    private Collection<String> tags;
    private String summary;
    private Double rating;

    public StoryGetDTO(Story story) {
        id = story.getId();
        title = story.getTitle();
        summary = story.getSummary();

        tags = story.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        Double ratingSum = story.getReviews().stream()
                .map(Review::getRating)
                .reduce(Double::sum)
                .orElse(0.0);

        int ratingCnt = story.getReviews().isEmpty() ? 1 : story.getReviews().size();
        rating = ratingSum / ratingCnt;
    }
}
