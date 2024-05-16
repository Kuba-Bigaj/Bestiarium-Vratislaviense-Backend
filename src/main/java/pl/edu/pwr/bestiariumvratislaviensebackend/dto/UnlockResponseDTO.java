package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

@Data
public class UnlockResponseDTO {
    private Integer creatureID;
    private String creatureName;
    private Integer userID;
}
