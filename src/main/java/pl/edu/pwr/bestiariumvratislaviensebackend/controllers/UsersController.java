package pl.edu.pwr.bestiariumvratislaviensebackend.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.ReviewGetShortDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.SeekerDetailsDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.UserDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.UserResponseDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Cryptid;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Role;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Seeker;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Story;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.SeekerRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.StoryRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.security.JWTGenerator;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
public class UsersController {
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final SeekerRepository seekerRepository;
    private final PasswordEncoder passwordEncoder;
    private final StoryRepository storyRepository;

    @Autowired
    public UsersController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, SeekerRepository seekerRepository, PasswordEncoder passwordEncoder, StoryRepository storyRepository, StoryRepository storyRepository1) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.seekerRepository = seekerRepository;
        this.passwordEncoder = passwordEncoder;
        this.storyRepository = storyRepository1;
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

    @GetMapping("/user")
    public ResponseEntity<SeekerDetailsDTO> get_user_details(@RequestParam String userID) {
        Long id = Long.parseLong(userID);
        Seeker target = seekerRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Collection<Story> userStories = storyRepository.findByAuthorId(target.getId());

        SeekerDetailsDTO response = new SeekerDetailsDTO();
        response.setName(target.getUsername());
        response.setCreatures(target.getUnlockedCryptids().stream()
                .map(Cryptid::getName).collect(Collectors.toSet()));
        response.setReviews(target.getReviews().stream()
                .map(ReviewGetShortDTO::new).collect(Collectors.toSet()));
        response.setStories(userStories.stream().map(Story::getTitle).collect(Collectors.toSet()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
