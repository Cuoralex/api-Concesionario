package concessionaire.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import concessionaire.api.model.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}