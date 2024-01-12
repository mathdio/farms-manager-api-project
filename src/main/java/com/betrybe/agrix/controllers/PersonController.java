package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.PersonRequestDto;
import com.betrybe.agrix.controllers.dto.PersonResponseDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create person response entity.
   *
   * @param personRequestDto the person request dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<PersonResponseDto> createPerson(
      @RequestBody PersonRequestDto personRequestDto
  ) {
    Person createdPerson = this.personService.create(personRequestDto.toPerson());
    PersonResponseDto personResponseDto = new PersonResponseDto(
        createdPerson.getId(),
        createdPerson.getUsername(),
        createdPerson.getRole()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(personResponseDto);
  }
}
