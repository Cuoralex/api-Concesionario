package concessionaire.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import concessionaire.api.model.Range;


@Repository
public interface RangeRepository extends JpaRepository<Range, Long> {
    Range findByDescription(String description);
}