package pl.edu.pwr.bestiariumvratislaviensebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String summary;

    @Column(columnDefinition = "text")
    private String content;

    @ManyToOne
    private Seeker author;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable()
    private Set<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "stories")
    private Collection<Cryptid> cryptids;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews;

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
        Story story = (Story) o;
        return Objects.equals(id, story.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
