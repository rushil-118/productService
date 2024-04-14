package com.sst.productservice.repositories;

import com.sst.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Override
    Category save(Category category);
}
