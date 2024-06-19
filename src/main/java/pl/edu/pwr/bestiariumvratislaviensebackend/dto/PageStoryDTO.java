package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class PageStoryDTO {
    Collection<StoryGetDTO> stories;
    Integer page;
    Integer pagesAmount;
}
