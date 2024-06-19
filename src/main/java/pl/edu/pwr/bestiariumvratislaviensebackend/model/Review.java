package pl.edu.pwr.bestiariumvratislaviensebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.Objects;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private String body;
    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "author_id")
    private Seeker author;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "story_id", referencedColumnName = "id")
    private Story story;

    private Instant created;
    private Instant modified;

    @PrePersist
    public void setCreated() {
        created = Instant.now();
    }

    @PreUpdate
    public void setModified() {
        modified = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
