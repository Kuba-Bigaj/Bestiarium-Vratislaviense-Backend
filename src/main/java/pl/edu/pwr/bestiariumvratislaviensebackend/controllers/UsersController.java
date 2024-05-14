package pl.edu.pwr.bestiariumvratislaviensebackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.UserDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.UserResponseDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Role;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Seeker;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.SeekerRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.security.JWTGenerator;

import java.util.Collections;

@RestController
public class UsersController {
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final SeekerRepository seekerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, SeekerRepository seekerRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.seekerRepository = seekerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        UserResponseDTO response = new UserResponseDTO();
        response.setUsername(userDTO.getUsername());
        response.setToken(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserDTO userDTO) {
        if (seekerRepository.existsByUsername(userDTO.getUsername())) {
            return new ResponseEntity<>(new UserResponseDTO(), HttpStatus.BAD_REQUEST);
        }

        Seeker seeker = new Seeker();

        seeker.setUsername(userDTO.getUsername());
        seeker.setPassword(passwordEncoder.encode((userDTO.getPassword())));

        Role role = new Role();
        role.setName("USER");
        seeker.setRoles(Collections.singleton(role));

        seekerRepository.save(seeker);

        return login(userDTO);
    }
}
