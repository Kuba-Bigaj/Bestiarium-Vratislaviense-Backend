package pl.edu.pwr.bestiariumvratislaviensebackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.ReviewPostDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Review;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Seeker;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Story;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.ReviewRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.SeekerRepository;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.StoryRepository;

@Service
public class ReviewService {

    private final SeekerRepository seekerRepository;
    private final StoryRepository storyRepository;
    private final ReviewRepository reviewRepository;

    public ReviewService(SeekerRepository seekerRepository, StoryRepository storyRepository, ReviewRepository reviewRepository, StoryService storyService) {
        this.seekerRepository = seekerRepository;
        this.storyRepository = storyRepository;
        this.reviewRepository = reviewRepository;
    }

    public void saveReview(ReviewPostDTO input) {
        Review review = new Review();

        review.setBody(input.getBody());
        review.setRating(input.getRating());

        review.setAuthor(seekerRepository.findById(input.getAuthorID()).orElseThrow(EntityNotFoundException::new));

        Story s = storyRepository.findById(input.getStoryID()).orElseThrow(EntityNotFoundException::new);
        Seeker a = seekerRepository.findById(input.getAuthorID()).orElseThrow(EntityNotFoundException::new);

        review.setStory(s);
        s.getReviews().add(review);

        review.setAuthor(a);
        a.getReviews().add(review);

        reviewRepository.save(review);
    }
}
