package concessionaire.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import concessionaire.api.model.Store;


@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByName(String name);
}