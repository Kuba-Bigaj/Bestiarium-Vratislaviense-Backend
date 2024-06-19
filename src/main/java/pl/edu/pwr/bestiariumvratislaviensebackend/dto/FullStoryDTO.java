package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Story;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class FullStoryDTO {
    private String title;
    private String body;
    private Set<ReviewGetDTO> reviews;

    public FullStoryDTO(Story story) {
        this.title = story.getTitle();
        System.out.println(story.getTitle());
        System.out.println(story.getAuthor().getUsername());
        System.out.println(story.getReviews().size());


        this.body = story.getContent();


        this.reviews = story.getReviews().stream()
                .map(ReviewGetDTO::new)
                .collect(Collectors.toSet());
    }
}
