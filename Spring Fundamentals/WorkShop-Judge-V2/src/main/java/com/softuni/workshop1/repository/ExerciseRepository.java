package com.softuni.workshop1.repository;

import com.softuni.workshop1.model.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, String> {
    Optional<Exercise> findByName(String name);
}
