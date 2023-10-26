package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAllByNameIn(Iterable<String> names);

}