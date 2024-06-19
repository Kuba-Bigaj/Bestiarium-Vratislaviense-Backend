package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.RankingView;

@Data
public class RankingDTO {
    private int position;
    private String name;
    private int creaturesAmount;
    private int reviewsAmount;

    public RankingDTO(RankingView rankingView){
        position = rankingView.getRank();
        name=rankingView.getUsername();
        creaturesAmount=rankingView.getCreaturesCount();
        reviewsAmount=rankingView.getReviewsCount();
    }
}
