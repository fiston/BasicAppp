package rw.viden.basiapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import rw.viden.basiapp.models.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
