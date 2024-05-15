package pl.edu.pwr.bestiariumvratislaviensebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

@Entity
@Getter
@Immutable
public class RankingView {
    @Id
    @GeneratedValue
    Long id;

    //TODO zaimplementować model i zrobić tu testy

    /**
     * tu się dzieją skomplikowane rzeczy z ustanawaianiem widoków,
     * łączeniem z samym soba (liczenie graczy lepszych od siebie) i ogólnie łączenie SQL z JPA
     * do dalszych testów potrzebny będzie gotowy model
     */
}
