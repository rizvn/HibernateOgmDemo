package com.rizvn.ogmdemo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * @author Riz
 */
@Entity
public class Dog {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "dog")
  @TableGenerator(
    name = "dog",
    table = "sequences",
    pkColumnName = "key",
    pkColumnValue = "dog",
    valueColumnName = "seed"
  )
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  private Long id;

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  private String name;

  @ManyToOne
  public Breed getBreed() { return breed; }
  public void setBreed(Breed breed) { this.breed = breed; }
  private Breed breed;

  @Override
  public String toString() {
    return new ToStringBuilder(this).
      append("id", id).
      append("name", name).
      toString();
  }
}