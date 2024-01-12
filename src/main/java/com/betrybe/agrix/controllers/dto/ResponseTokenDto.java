package com.betrybe.agrix.controllers.dto;

/**
 * The type Response dto.
 *
 * @param <T> the type parameter
 */
public record ResponseTokenDto<T>(String message, T token) {

}
