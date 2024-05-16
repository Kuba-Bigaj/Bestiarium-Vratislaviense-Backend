package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

@Data
public class UnlockRequestDTO {
    private String name;
    private String code;
    private Integer userID;
}
