package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.stream.Stream;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Stream<Tag> findAllByNameIn(Collection<String> name);

}