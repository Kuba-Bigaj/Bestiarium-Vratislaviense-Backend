package pl.edu.pwr.bestiariumvratislaviensebackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Set;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {

    Set<T> findAll();

    Set<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    long count();
}