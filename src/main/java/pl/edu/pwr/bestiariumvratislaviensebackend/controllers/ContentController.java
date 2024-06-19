package pl.edu.pwr.bestiariumvratislaviensebackend.controllers;

import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.*;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Cryptid;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Seeker;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.CryptidRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.SeekerRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.StoryRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.security.SeekerDetailsService;
import pl.edu.pwr.bestiariumvratislaviensebackend.services.CryptidService;
import pl.edu.pwr.bestiariumvratislaviensebackend.services.ReviewService;
import pl.edu.pwr.bestiariumvratislaviensebackend.services.StoryService;

@RestController
public class ContentController {
    private final CryptidService cryptidService;
    private final StoryService storyService;
    private final StoryRepository storyRepository;
    private final ReviewService reviewService;
    private final SeekerDetailsService seekerDetailsService;
    private final SeekerRepository seekerRepository;
    private final CryptidRepository cryptidRepository;

    @Autowired
    public ContentController(CryptidService cryptidService, StoryService storyService, StoryRepository storyRepository, ReviewService reviewService, SeekerDetailsService seekerDetailsService, SeekerRepository seekerRepository, CryptidRepository cryptidRepository) {
        this.cryptidService = cryptidService;
        this.storyService = storyService;
        this.storyRepository = storyRepository;
        this.reviewService = reviewService;
        this.seekerDetailsService = seekerDetailsService;
        this.seekerRepository = seekerRepository;
        this.cryptidRepository = cryptidRepository;
    }

    @GetMapping("/creatures")
    public ResponseEntity<PageCryptidDTO> get_cryptids(@RequestParam(defaultValue = "0") String page, @RequestParam(defaultValue = "") String regex) {
        int p_num = Integer.parseInt(page);

        PageCryptidDTO response = cryptidService.getCryptids(regex, p_num);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/stories")
    public ResponseEntity<PageStoryDTO> get_stories(@RequestParam(defaultValue = "0") String page, @RequestParam(defaultValue = "0") String creatureID) {
        int page_num = Integer.parseInt(page);
        Long cryptid_id = Long.parseLong(creatureID);

        PageStoryDTO response = storyService.getStories(cryptid_id, page_num);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/stories/{id}")
    public ResponseEntity<FullStoryDTO> get_story(@PathVariable String id) {
        Long story_id = Long.parseLong(id);

        FullStoryDTO response = storyService.getFullStory(story_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO VERY problematic
    @GetMapping("/ranking")
    public ResponseEntity<RankingDTO> get_ranking(@RequestParam(defaultValue = "0") String page) {
        Integer page_num = Integer.parseInt(page);
        RankingDTO response = new RankingDTO();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/story") //TODO
    public ResponseEntity<StoryResponseDTO> post_story(@RequestBody StoryGetDTO story) {

        StoryResponseDTO response = new StoryResponseDTO();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewResponseDTO> post_review(@RequestBody ReviewPostDTO review) {

        reviewService.saveReview(review);

        ReviewResponseDTO response = new ReviewResponseDTO();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/unlock")
    public ResponseEntity<UnlockResponseDTO> unlock(@RequestParam UnlockRequestDTO request) {

        Seeker seeker = seekerRepository.findByUsername(request.getName()).orElseThrow(EntityExistsException::new);
        Cryptid target = cryptidRepository.findByName(request.getName());

        if (request.getCode().equals(target.getUnlockCode())) {
            seeker.getUnlockedCryptids().add(target);
        }

        UnlockResponseDTO response = new UnlockResponseDTO();
        response.setCreatureID(target.getId());
        response.setUserID(seeker.getId());
        response.setCreatureName(target.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
