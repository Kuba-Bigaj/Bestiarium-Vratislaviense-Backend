package pl.edu.pwr.bestiariumvratislaviensebackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.RankingView;

public interface RankingViewRepository extends ReadOnlyRepository<RankingView, Long> {
    Page<RankingView> findByUsernameContaining(String regex, Pageable pageable);
}
