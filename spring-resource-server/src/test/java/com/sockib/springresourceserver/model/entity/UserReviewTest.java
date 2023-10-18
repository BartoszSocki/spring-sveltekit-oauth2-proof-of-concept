package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.valueobject.FiveStarScore;
import com.sockib.springresourceserver.repositories.ReviewRepository;
import com.sockib.springresourceserver.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserReviewTest {


    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void givenUserReview_whenPersist_thenSuccess() {
        // given
        final String description = "desc";
        final String email = "email.com";
        final FiveStarScore fiveStarScore = new FiveStarScore(5);

        var user = new User();
        user.setEmail(email);

        var persistedUser = userRepository.save(user);

        var review = new UserReview();
        review.setUser(persistedUser);
        review.setDescription(description);
        review.setFiveStarScore(fiveStarScore);

        // when
        var persistedReview = reviewRepository.save(review);

        // then
        var retrievedReview = reviewRepository.findById(persistedReview.getId());

        assertThat(retrievedReview).isPresent();
        assertThat(retrievedReview.get().getDescription()).isEqualTo(description);
        assertThat(((UserReview) retrievedReview.get()).getUser().getEmail()).isEqualTo(email);
    }

}