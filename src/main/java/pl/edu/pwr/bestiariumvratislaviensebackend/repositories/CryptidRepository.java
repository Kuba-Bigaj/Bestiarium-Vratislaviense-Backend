package pl.edu.pwr.bestiariumvratislaviensebackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Cryptid;

public interface CryptidRepository extends PagingAndSortingRepository<Cryptid, Long> {
    Page<Cryptid> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Cryptid findByName(String name);
}
