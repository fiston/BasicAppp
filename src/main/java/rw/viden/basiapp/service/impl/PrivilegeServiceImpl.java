package rw.viden.basiapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.viden.basiapp.dao.PrivilegeRepository;
import rw.viden.basiapp.models.Privilege;
import rw.viden.basiapp.service.PrivilegeService;

/**
 * Created by nic on 6/7/16.
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    @Autowired
    PrivilegeRepository privilegeRepository;
    @Override
    public Privilege findByName(String name) {
        return privilegeRepository.findByName(name);
    }

    @Override
    public void save(Privilege privilege) {
        privilegeRepository.save(privilege);
    }
}
