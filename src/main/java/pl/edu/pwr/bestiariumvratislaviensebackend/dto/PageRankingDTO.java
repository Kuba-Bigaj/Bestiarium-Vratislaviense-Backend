package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class PageRankingDTO {
    Collection<RankingDTO> users;
    Integer page;
    Integer pagesAmount;
}
