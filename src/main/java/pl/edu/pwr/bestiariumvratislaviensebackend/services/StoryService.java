package pl.edu.pwr.bestiariumvratislaviensebackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.FullStoryDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.PageStoryDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.StoryGetDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Review;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Story;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.StoryRepository;

import java.util.stream.Collectors;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    @Autowired
    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public PageStoryDTO getStories(Long creatureID, int page) {
        Page<Story> stories = storyRepository.findByCryptidID(creatureID, PageRequest.of(page, 10));

        PageStoryDTO result = new PageStoryDTO();
        result.setStories(stories.getContent().stream().map(StoryGetDTO::new).collect(Collectors.toSet()));
        result.setPage(stories.getNumber());
        result.setPagesAmount(stories.getTotalPages());

        return result;
    }

    public FullStoryDTO getFullStory(Long storyId) {
        return new FullStoryDTO(storyRepository.findById(storyId).orElseThrow(EntityNotFoundException::new));
    }

    public void addReview(Review r, Long id) {
        Story s = storyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        s.getReviews().add(r);
    }
}
