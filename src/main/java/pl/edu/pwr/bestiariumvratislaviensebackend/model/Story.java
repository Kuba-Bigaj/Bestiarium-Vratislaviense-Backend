package pl.edu.pwr.bestiariumvratislaviensebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue
    private Long id;
}
