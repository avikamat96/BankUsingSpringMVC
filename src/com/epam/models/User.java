package com.epam.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Bean;

import com.epam.enums.Gender;

/**
 * The Class Users.
 *
 * @author Avinash_Kamat
 */
@Entity
@Table(name = "user")
public class User{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private int user_id;
  
  @Column(name = "user_name")
  private String name;
  
  @Column(name = "user_age")
  private int age;
  
  @Column(name = "user_gender")
  @Enumerated
  private Gender gender;
  
  @Column
  @CreationTimestamp
  private LocalDateTime createDateTime;

  @Column
  @UpdateTimestamp
  private LocalDateTime updateDateTime;

  /**
   * Instantiates a new users.
   */
  public User() {

  }
  
  /**
   * @param name
   * @param age
   * @param gender
   */
  public User(String name, int age, Gender gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the age.
   *
   * @return the age
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets the age.
   *
   * @param age the age to set
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Gets the gender.
   *
   * @return the gender
   */
  public Gender getGender() {
    return gender;
  }

  /**
   * Sets the gender.
   *
   * @param gender the gender to set
   */
  public void setGender(Gender gender) {
    this.gender = gender;
  }

  /**
   * @return the user_id
   */
  public int getUser_id() {
    return user_id;
  }

  /**
   * @param user_id the user_id to set
   */
  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  /**
   * @return the createDateTime
   */
  public LocalDateTime getCreateDateTime() {
    return createDateTime;
  }

  /**
   * @param createDateTime the createDateTime to set
   */
  public void setCreateDateTime(LocalDateTime createDateTime) {
    this.createDateTime = createDateTime;
  }

  /**
   * @return the updateDateTime
   */
  public LocalDateTime getUpdateDateTime() {
    return updateDateTime;
  }

  /**
   * @param updateDateTime the updateDateTime to set
   */
  public void setUpdateDateTime(LocalDateTime updateDateTime) {
    this.updateDateTime = updateDateTime;
  }
  
  

}
