package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

@Data
public class UnlockResponseDTO {
    private Long userID;
    private Long creatureID;
    private String creatureName;
}
