package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void givenUser_whenPersist_thenSuccess() {
        // given
        final String email = "email.com";
        var user = new User();
        user.setEmail(email);

        // when
        var persistedUser = userRepository.save(user);

        // then
        var retrievedUser = userRepository.findById(persistedUser.getId());
        assertThat(retrievedUser).isPresent();
        assertThat(retrievedUser.get().getEmail()).isEqualTo(email);

        assert true;
    }

    @Test
    void givenUsersWithTheSameEmail_whenPersist_thenError() {
        // given
        final String email = "email.com";
        var user1 = new User();
        user1.setEmail(email);

        var user2 = new User();
        user2.setEmail(email);

        // when
        userRepository.save(user1);

        // then
        assertThatThrownBy(() -> {
            userRepository.save(user2);
        }).isInstanceOf(RuntimeException.class);
    }

}