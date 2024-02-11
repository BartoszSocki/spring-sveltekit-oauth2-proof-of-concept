package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findCategoryByName(String name);

}