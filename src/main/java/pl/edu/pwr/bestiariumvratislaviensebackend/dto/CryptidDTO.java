package pl.edu.pwr.bestiariumvratislaviensebackend.dto;

import lombok.Data;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Cryptid;

@Data
public class CryptidDTO {
    private Long id;
    private String name;
    private Location location;
    private Integer storiesAmount;

    public CryptidDTO(Cryptid cryptid) {
        id = cryptid.getId();
        name = cryptid.getName();
        location = new Location(cryptid.getLatitude(), cryptid.getLongitude());
        storiesAmount = cryptid.getStories().size();
    }

    @Data
    private class Location {
        private Double lat;
        private Double lon;

        Location(Double lat, Double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        Location() {
            this(0.0, 0.0);
        }
    }
}
