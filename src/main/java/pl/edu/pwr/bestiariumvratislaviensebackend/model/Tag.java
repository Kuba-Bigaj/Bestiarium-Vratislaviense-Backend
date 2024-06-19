package pl.edu.pwr.bestiariumvratislaviensebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {}
}
