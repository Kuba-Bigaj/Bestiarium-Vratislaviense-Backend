package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class StoryDTO {
    private String title;
    private String summary;
    private Collection<String> tags;
    private String body;
    private int authorID;
    private int creatureID;
}
