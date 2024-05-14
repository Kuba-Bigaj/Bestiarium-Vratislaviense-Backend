package pl.edu.pwr.bestiariumvratislaviensebackend.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Role;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Seeker;

import java.util.Collection;

public class SeekerDetails implements UserDetails {

    private final Seeker seeker;

    public SeekerDetails(Seeker seeker) {this.seeker = seeker;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return
                seeker
                        .getRoles().stream()
                        .map(Role::getName)
                        .map(SimpleGrantedAuthority::new)
                        .toList();
    }

    @Override
    public String getPassword() {
        return seeker.getPassword();
    }

    @Override
    public String getUsername() {
        return seeker.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
