package pl.edu.pwr.bestiariumvratislaviensebackend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Immutable;
import lombok.Data;

@Data
@Entity
@Immutable
@Table(name = "RANKING_VIEW")
public class RankingView {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "cryptids_count")
    private int creaturesCount;

    @Column(name = "reviewsCount")
    private int reviewsCount;

    @Column(name = "rank")
    private int rank;

}
