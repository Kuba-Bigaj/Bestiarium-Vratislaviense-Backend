package pl.edu.pwr.bestiariumvratislaviensebackend.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Tag;

import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
