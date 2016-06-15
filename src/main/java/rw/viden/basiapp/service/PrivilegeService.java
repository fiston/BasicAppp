package rw.viden.basiapp.service;

import rw.viden.basiapp.models.Privilege;

/**
 * Created by nic on 6/7/16.
 */
public interface PrivilegeService {

    Privilege findByName(String name);

    void save(Privilege privilege);
}
