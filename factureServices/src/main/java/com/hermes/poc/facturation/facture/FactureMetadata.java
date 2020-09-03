package com.hermes.poc.facturation.facture;

import lombok.Data;

@Data
public class FactureMetadata {
    private Long id;
    private String fichierId;
    private String numeroFacture;
}
