package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.AuthenticationDto;
import com.betrybe.agrix.controllers.dto.ResponseTokenDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }


  /**
   * Login response entity.
   *
   * @param authenticationDto the authentication dto
   * @return the response entity
   */
  @PostMapping("/login")
  public ResponseEntity<ResponseTokenDto> login(
      @RequestBody AuthenticationDto authenticationDto
  ) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(
            authenticationDto.username(),
            authenticationDto.password()
        );

    Authentication auth = this.authenticationManager.authenticate(usernamePassword);

    Person person = (Person) auth.getPrincipal();
    String token = this.tokenService.generateToken(person);

    ResponseTokenDto<String> responseDto = new ResponseTokenDto<>("Pessoa autenticada com sucesso!",
        token);

    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }
}
