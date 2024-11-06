package concessionaire.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import concessionaire.api.model.City;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
}