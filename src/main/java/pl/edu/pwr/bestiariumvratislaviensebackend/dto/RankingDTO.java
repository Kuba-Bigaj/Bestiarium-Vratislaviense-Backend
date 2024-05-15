package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.RankingView;

import java.util.Collection;

@Data
public class RankingDTO {
    Collection<RankingView> users;
    Integer page;
    Integer pagesAmount;
}
