package com.bezkoder.spring.jpa.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.h2.model.Tutorial;

/**
 * Repository interface for {@link Tutorial} entities.
 *
 * This interface extends JpaRepository, providing CRUD operations and additional
 * query methods for Tutorial entities.
 */
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

  /**
   * Finds all tutorials that are either published or unpublished.
   *
   * @param published The publication status to filter by.
   * @return A list of {@link Tutorial} entities matching the publication status.
   */
  List<Tutorial> findByPublished(boolean published);


  /**
   * Finds all tutorials containing the specified title, case-insensitive.
   *
   * @param title The title string to search for within the tutorial titles.
   * @return A list of {@link Tutorial} entities where the title contains the specified string.
   */
  List<Tutorial> findByTitleContainingIgnoreCase(String title);
}
