package concessionaire.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import concessionaire.api.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByDescription(String description);
}