package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Cryptid;

import java.util.Collection;

@Data
public class PageCryptidDTO {
    Collection<CryptidDTO> creatures;
    Integer page;
    Integer pagesAmount;
}
