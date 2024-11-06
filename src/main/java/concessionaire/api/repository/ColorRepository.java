package concessionaire.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import concessionaire.api.model.Color;


@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    Color findByDescription(String description);
}