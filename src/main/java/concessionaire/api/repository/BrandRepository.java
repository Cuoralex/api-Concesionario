package concessionaire.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import concessionaire.api.model.Brand;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByDescription(String description);
}