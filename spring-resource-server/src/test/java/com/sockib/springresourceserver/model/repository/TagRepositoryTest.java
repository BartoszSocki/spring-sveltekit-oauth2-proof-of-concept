package com.sockib.springresourceserver.model.repository;

import com.sockib.springresourceserver.model.respository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;

    @Test
    @Sql("/repository/tags_test_2.sql")
    void givenTagsNames_whenFindByNameIn_thenSuccess() {
        // given
        List<String> tagNames = new ArrayList<>();
        tagNames.add("Germany");
        tagNames.add("Poland");
        tagNames.add("USA");

        // when
        var tags = tagRepository.findAllByNameIn(tagNames);

        // then
        assertThat(tags).hasSize(tagNames.size());
    }

}
