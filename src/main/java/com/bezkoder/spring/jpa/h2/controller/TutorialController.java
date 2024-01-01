package com.bezkoder.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.h2.model.Tutorial;
import com.bezkoder.spring.jpa.h2.repository.TutorialRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Controller for managing tutorials.
 * <p>
 * This class provides RESTful web services to create, retrieve, update, and delete tutorials.
 * It interacts with the TutorialRepository to perform these operations.
 * </p>
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

  private static final Logger logger = LoggerFactory.getLogger(TutorialController.class);

  @Autowired
  TutorialRepository tutorialRepository;
  /**
   * Get all tutorials or search by title.
   *
   * @param title Optional search string to filter tutorials by title.
   *              If this parameter is null, all tutorials are retrieved.
   * @return ResponseEntity containing the list of tutorials and the HTTP status.
   */
  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
    logger.info("Fetching all tutorials, title filter: {}", title);
    try {
      List<Tutorial> tutorials = new ArrayList<Tutorial>();

      if (title == null)
        tutorialRepository.findAll().forEach(tutorials::add);
      else
        tutorialRepository.findByTitleContainingIgnoreCase(title).forEach(tutorials::add);

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      logger.info("Fetched {} tutorials", tutorials.size());
      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Error occurred: ", e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  /**
   * Get a tutorial by its ID.
   *
   * @param id The ID of the tutorial.
   * @return ResponseEntity containing the tutorial and the HTTP status.
   */
  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
    Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
    logger.info("Fetching tutorial with id: {}", id);
    if (tutorialData.isPresent()) {
      logger.info("Found tutorial with id: {}", id);
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      logger.warn("No tutorial found with id: {}", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Create a new tutorial.
   *
   * @param tutorial The tutorial to be created.
   * @return ResponseEntity containing the created tutorial and the HTTP status.
   */
  @PostMapping("/tutorials")
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    try {
      logger.info("Creating new tutorial with title: {}", tutorial.getTitle());
      Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
      logger.info("Created tutorial with id: {}", _tutorial.getId());
      return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    }catch(RuntimeException e){
      logger.error("Runtime exception occurred while creating tutorial: {}", e.getMessage(), e);
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  /**
   * Update a tutorial.
   *
   * @param id The ID of the tutorial to be updated.
   * @param tutorial The tutorial data to be updated.
   * @return ResponseEntity containing the updated tutorial and the HTTP status.
   *         Returns HttpStatus.NOT_FOUND if the tutorial is not found.
   */
  @PutMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
    Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

    if (tutorialData.isPresent()) {
      Tutorial _tutorial = tutorialData.get();
      _tutorial.setTitle(tutorial.getTitle());
      _tutorial.setDescription(tutorial.getDescription());
      _tutorial.setPublished(tutorial.isPublished());
      return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Delete a tutorial by its ID.
   *
   * @param id The ID of the tutorial to be deleted.
   * @return ResponseEntity with HTTP status indicating the result of the operation.
   */
  @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    try {
      tutorialRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Delete all tutorials.
   *
   * @return ResponseEntity with HTTP status indicating the result of the operation.
   */
  @DeleteMapping("/tutorials")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    try {
      tutorialRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    catch (RuntimeException e) {
      logger.error("Runtime exception occurred while creating tutorial: {}", e.getMessage(), e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  /**
   * Find all published tutorials.
   *
   * @return ResponseEntity containing the list of published tutorials and the HTTP status.
   */
  @GetMapping("/tutorials/published")
  public ResponseEntity<List<Tutorial>> findByPublished() {
    try {
      List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (RuntimeException e) {
      logger.error("Runtime exception occurred while creating tutorial: {}", e.getMessage(), e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
