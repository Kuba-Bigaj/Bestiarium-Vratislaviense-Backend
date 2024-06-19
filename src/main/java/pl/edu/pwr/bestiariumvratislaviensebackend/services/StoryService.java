package pl.edu.pwr.bestiariumvratislaviensebackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.FullStoryDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.PageStoryDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.StoryGetDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.StoryPostDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Story;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Tag;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.CryptidRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.SeekerRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.StoryRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.TagRepository;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class StoryService {

    private final StoryRepository storyRepository;
    private final SeekerRepository seekerRepository;
    private final CryptidRepository cryptidRepository;
    private final TagRepository tagRepository;

    @Autowired
    public StoryService(StoryRepository storyRepository, SeekerRepository seekerRepository, CryptidRepository cryptidRepository, TagRepository tagRepository) {
        this.storyRepository = storyRepository;
        this.seekerRepository = seekerRepository;
        this.cryptidRepository = cryptidRepository;
        this.tagRepository = tagRepository;
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

    public Story addStory(StoryPostDTO input) {
        Story story = new Story();

        story.setTitle(input.getTitle());
        story.setSummary(input.getSummary());
        story.setContent(input.getBody());

        story.setAuthor(seekerRepository.findById(input.getAuthorID()).orElseThrow(EntityNotFoundException::new));

        story.setCryptids(new HashSet<>());
        story.getCryptids().add(cryptidRepository.findById(input.getCreatureID()).orElseThrow(EntityNotFoundException::new));

        story.setTags(new HashSet<>());
        for (String tag : input.getTags()) {
            story.getTags().add(findOrCreateTag(tag));
        }

        storyRepository.save(story);
        return story;
    }

    private Tag findOrCreateTag(String tag)
    {
        return tagRepository.findByName(tag).orElse(new Tag(tag));
    }
}
