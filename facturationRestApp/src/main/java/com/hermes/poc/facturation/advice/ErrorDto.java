package com.hermes.poc.facturation.advice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDto {
    @JsonProperty("code")
    private int errorCode;
    @JsonProperty("message")
    private String errorMessage;

}
