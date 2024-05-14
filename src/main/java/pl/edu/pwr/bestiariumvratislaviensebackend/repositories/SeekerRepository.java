package pl.edu.pwr.bestiariumvratislaviensebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Seeker;

import java.util.Optional;

@Repository
public interface SeekerRepository extends JpaRepository<Seeker, Long> {
    Optional<Seeker> findByUsername(String username);
    Boolean existsByUsername(String username);
}
