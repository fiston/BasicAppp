package rw.viden.basiapp.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

/**
 * Created by nic on 6/7/16.
 */
public class CurrentUser implements UserDetails {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CurrentUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getAuthorities(user.getRoles());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        final List<Privilege> privilegeArrayList = new ArrayList<Privilege>();
        final List<String> privileges = new ArrayList<String>();
        for (final Role role : roles) {
            privilegeArrayList.addAll(role.getPrivileges());
        }
        privileges.addAll(privilegeArrayList.stream().map(Privilege::getName).collect(toCollection(ArrayList::new)));

        return getGrantedAuthorities(privileges);
    }


    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = privileges.stream().map(SimpleGrantedAuthority::new).collect(toCollection(ArrayList::new));
        return authorities;
    }
}
