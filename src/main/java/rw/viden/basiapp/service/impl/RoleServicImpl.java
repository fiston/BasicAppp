package rw.viden.basiapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.viden.basiapp.dao.RoleRepository;
import rw.viden.basiapp.models.Role;
import rw.viden.basiapp.service.RoleService;

/**
 * Created by nic on 6/7/16.
 */
@Service
public class RoleServicImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        return roleRepository.findByRoleName(name);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
