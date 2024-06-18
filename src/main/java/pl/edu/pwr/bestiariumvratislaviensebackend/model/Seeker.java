package pl.edu.pwr.bestiariumvratislaviensebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "seekers")
public class Seeker {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable()
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable()
    private Set<Cryptid> unlockedCryptids;

    @OneToMany()
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
}
