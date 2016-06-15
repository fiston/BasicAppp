package rw.viden.basiapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import rw.viden.basiapp.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);

    @Override
    void delete(Role role);

}
