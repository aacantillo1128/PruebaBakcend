package com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiError {
    private String message;
}
