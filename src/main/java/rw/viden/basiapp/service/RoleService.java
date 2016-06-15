package rw.viden.basiapp.service;

import rw.viden.basiapp.models.Role;

/**
 * Created by nic on 6/7/16.
 */
public interface RoleService {

    Role findByName(String name);

    void save(Role role);
}
