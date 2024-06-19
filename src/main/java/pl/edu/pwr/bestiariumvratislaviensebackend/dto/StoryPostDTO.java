package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

import java.util.Set;

@Data
public class StoryPostDTO {
private String title;
private String summary;
private String body;

private Set<String> tags;

private Long authorID;
private Long creatureID;

}
