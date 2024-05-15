package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Story;

import java.util.Collection;

@Data
public class PageStoryDTO {
    Collection<Story> stories;
    Integer page;
    Integer pagesAmount;
}
