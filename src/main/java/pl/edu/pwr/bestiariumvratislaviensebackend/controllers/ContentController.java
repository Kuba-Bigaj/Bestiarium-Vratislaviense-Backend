package pl.edu.pwr.bestiariumvratislaviensebackend.controllers;

import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.*;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Cryptid;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.RankingView;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Seeker;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Story;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.CryptidRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.RankingViewRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.SeekerRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.services.CryptidService;
import pl.edu.pwr.bestiariumvratislaviensebackend.services.ReviewService;
import pl.edu.pwr.bestiariumvratislaviensebackend.services.StoryService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class ContentController {
    private final CryptidService cryptidService;
    private final StoryService storyService;
    private final ReviewService reviewService;
    private final SeekerRepository seekerRepository;
    private final CryptidRepository cryptidRepository;
    private final RankingViewRepository rankingViewRepository;

    @Autowired
    public ContentController(CryptidService cryptidService, StoryService storyService, ReviewService reviewService, SeekerRepository seekerRepository, CryptidRepository cryptidRepository, RankingViewRepository rankingViewRepository) {
        this.cryptidService = cryptidService;
        this.storyService = storyService;
        this.reviewService = reviewService;
        this.seekerRepository = seekerRepository;
        this.cryptidRepository = cryptidRepository;
        this.rankingViewRepository = rankingViewRepository;
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

    @GetMapping("/ranking")
    public ResponseEntity<PageRankingDTO> get_ranking(@RequestParam(defaultValue = "0") String page, @RequestParam(defaultValue = "") String regex) {
        Integer page_num = Integer.parseInt(page);

        Page<RankingView> rankingPage = rankingViewRepository.findByUsernameContaining(regex, PageRequest.of(page_num, 10));

        PageRankingDTO response = new PageRankingDTO();

        response.setPage(rankingPage.getNumber());
        response.setPagesAmount(rankingPage.getTotalPages());
        response.setUsers(rankingPage.getContent().stream()
                .map(RankingDTO::new)
                .collect(Collectors.toSet()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/story")
    public ResponseEntity<StoryResponseDTO> post_story(@RequestBody StoryPostDTO story) {

        Story product = storyService.addStory(story);

        StoryResponseDTO response = new StoryResponseDTO();
        response.setId(product.getId());
        response.setTitle(product.getTitle());
        response.setAuthorID(product.getAuthor().getId());
        response.setCreatureID(product.getCryptids().stream()
                .findFirst().orElseThrow(EntityExistsException::new)
                .getId());

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
