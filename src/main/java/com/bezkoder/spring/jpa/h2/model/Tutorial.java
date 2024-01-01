package com.bezkoder.spring.jpa.h2.model;

import jakarta.persistence.*;

/**
 * Tutorial entity representing a tutorial.
 *
 * This class is annotated as an Entity for JPA to recognize it as a persistent entity.
 * It maps to the 'tutorials' table in the database.
 */
@Entity
@Table(name = "tutorials")
public class Tutorial {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "published")
  private boolean published;

  /**
   * Default constructor.
   */
  public Tutorial() {

  }

  /**
   * Constructor to initialize a Tutorial.
   *
   * @param title       Title of the tutorial.
   * @param description Description of the tutorial.
   * @param published   Publication status of the tutorial.
   */
  public Tutorial(String title, String description, boolean published) {
    this.title = title;
    this.description = description;
    this.published = published;
  }

  /**
   * Gets the ID of the tutorial.
   *
   * @return A long representing the ID of the tutorial.
   */
  public long getId() {
    return id;
  }

  /**
   * Gets the title of the tutorial.
   *
   * @return A String representing the title of the tutorial.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the tutorial.
   *
   * @param title New title of the tutorial.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets the description of the tutorial.
   *
   * @return A String representing the description of the tutorial.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the tutorial.
   *
   * @param description New description of the tutorial.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Checks if the tutorial is published.
   *
   * @return A boolean, true if the tutorial is published, false otherwise.
   */
  public boolean isPublished() {
    return published;
  }

  /**
   * Sets the publication status of the tutorial.
   *
   * @param isPublished New publication status to set.
   */
  public void setPublished(boolean isPublished) {
    this.published = isPublished;
  }

  /**
   * Returns a string representation of the Tutorial object.
   *
   * @return A String containing the ID, title, description, and publication status of the tutorial.
   */
  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
  }

}
