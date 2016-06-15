package rw.viden.basiapp.service.currentuser;


import rw.viden.basiapp.models.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
