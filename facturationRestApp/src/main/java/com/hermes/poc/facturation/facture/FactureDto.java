package com.hermes.poc.facturation.facture;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FactureDto {

    @JsonProperty("lienFichier")
    private String lienFichier;

    @JsonProperty("numeroFacture")
    private String numeroFacture;
}
