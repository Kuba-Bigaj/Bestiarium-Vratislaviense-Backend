package pl.edu.pwr.bestiariumvratislaviensebackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.SeekerRepository;

@Service
public class SeekerDetailsService implements UserDetailsService {

    private final SeekerRepository seekerRepository;

    @Autowired
    public SeekerDetailsService(SeekerRepository seekerRepository) {this.seekerRepository = seekerRepository;}


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return seekerRepository.findByUsername(username)
                .map(SeekerDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("Seeker %s not found!", username)));
    }
}
