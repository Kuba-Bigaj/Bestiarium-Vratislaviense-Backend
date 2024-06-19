package pl.edu.pwr.bestiariumvratislaviensebackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Cryptid;

import java.util.Optional;

public interface CryptidRepository extends PagingAndSortingRepository<Cryptid, Long> {
    Optional<Cryptid> findById(Long id);

    Page<Cryptid> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Cryptid findByName(String name);
}
