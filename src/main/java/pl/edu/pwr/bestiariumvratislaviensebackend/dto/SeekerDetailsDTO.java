package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;
import java.util.Collection;

@Data
public class SeekerDetailsDTO {
    private String name;
    private Collection<String> creatures;
    private Collection<String> stories;
    private Collection<ReviewGetShortDTO> reviews;
}
