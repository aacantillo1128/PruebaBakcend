package com.alejo.svautenticacion.domain.model.domain.token;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Token {
    private String token;
}
