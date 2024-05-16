package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

@Data
public class StoryResponseDTO {
    private int id;
    private String title;
    private int authorID;
    private int creatureID;
}
