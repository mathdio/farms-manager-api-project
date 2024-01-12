package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * The type Person request dto.
 */
public record PersonRequestDto(Long id, String username, String password, Role role) {

  public Person toPerson() {
    return new Person(id, username, password, role);
  }
}
