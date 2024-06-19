package pl.edu.pwr.bestiariumvratislaviensebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Review;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long>, JpaRepository<Review, Long> {

}
