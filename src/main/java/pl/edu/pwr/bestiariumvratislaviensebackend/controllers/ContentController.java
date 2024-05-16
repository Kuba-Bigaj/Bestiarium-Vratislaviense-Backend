package pl.edu.pwr.bestiariumvratislaviensebackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.*;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Story;

@RestController
public class ContentController {

    @GetMapping("/creatures")
    public ResponseEntity<PageCryptidDTO> get_cryptids(@RequestParam(defaultValue = "0") String page, @RequestParam(defaultValue = "") String regex) {
        int p_num = Integer.parseInt(page);

        PageCryptidDTO response = new PageCryptidDTO();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/stories")
    public ResponseEntity<PageStoryDTO> get_stories(@RequestParam(defaultValue = "0") String page, @RequestParam(defaultValue = "0") String id) {
        int page_num = Integer.parseInt(page);
        int cryptid_id = Integer.parseInt(id);

        PageStoryDTO response = new PageStoryDTO();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/stories/{id}")
    public ResponseEntity<Story> get_story(@PathVariable String id) {
        int story_id = Integer.parseInt(id);

        Story response = new Story();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO VERY problematic
    @GetMapping("/ranking")
    public ResponseEntity<RankingDTO> get_ranking(@RequestParam(defaultValue = "0") String page) {
        Integer page_num = Integer.parseInt(page);
        RankingDTO response = new RankingDTO();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/story")
    public ResponseEntity<StoryResponseDTO> post_story(@RequestBody StoryDTO story) {

        StoryResponseDTO response = new StoryResponseDTO();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewResponseDTO> post_review(@RequestBody ReviewDTO review) {

        ReviewResponseDTO response = new ReviewResponseDTO();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/unlock")
    public ResponseEntity<UnlockResponseDTO> unlock(@RequestParam UnlockRequestDTO request) {

        UnlockResponseDTO response = new UnlockResponseDTO();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
