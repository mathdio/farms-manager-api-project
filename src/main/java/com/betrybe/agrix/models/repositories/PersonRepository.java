package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Person JPA repository.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  Person findByUsername(String username);
}
