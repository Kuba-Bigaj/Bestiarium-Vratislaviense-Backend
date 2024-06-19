package pl.edu.pwr.bestiariumvratislaviensebackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Story;

import java.util.Collection;

public interface StoryRepository extends PagingAndSortingRepository<Story, Long>, JpaRepository<Story, Long> {
    //Story findById(Long Id);

    @Query(value = "select s from Story s inner join s.cryptids c where ?1 = c.id")
    Page<Story> findByCryptidID(Long cryptidID, Pageable pageable);

    Collection<Story> findByAuthorId(Long id);
}
