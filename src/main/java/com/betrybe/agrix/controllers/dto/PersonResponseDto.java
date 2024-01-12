package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.security.Role;

/**
 * The type Person response dto.
 */
public record PersonResponseDto(Long id, String username, Role role) {

}
