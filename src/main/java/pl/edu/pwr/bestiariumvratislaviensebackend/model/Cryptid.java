package pl.edu.pwr.bestiariumvratislaviensebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
@Entity
@Table(name = "cryptids")
public class Cryptid {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double longitude;
    private Double latitude;

    private String unlockCode;

    private Instant created;
    private Instant modified;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable()
    private Set<Story> stories;

    @PrePersist
    public void setCreated() {
        created = Instant.now();
    }

    @PreUpdate
    public void setModified() {
        modified = Instant.now();
    }

}
