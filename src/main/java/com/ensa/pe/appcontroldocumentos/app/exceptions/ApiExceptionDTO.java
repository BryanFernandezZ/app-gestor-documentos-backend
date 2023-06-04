package com.ensa.pe.appcontroldocumentos.app.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiExceptionDTO {
    private String message;
    private String timestamp;
}
