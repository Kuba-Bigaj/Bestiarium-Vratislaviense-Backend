package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ReviewShortDTO {
    private String title;
    private Instant date;
}
