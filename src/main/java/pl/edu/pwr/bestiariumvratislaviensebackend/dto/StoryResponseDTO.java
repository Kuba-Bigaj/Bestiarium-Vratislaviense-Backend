package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

@Data
public class StoryResponseDTO {
    private Long id;
    private String title;
    private Long authorID;
    private Long creatureID;
}
