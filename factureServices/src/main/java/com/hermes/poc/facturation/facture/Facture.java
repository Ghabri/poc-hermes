package com.hermes.poc.facturation.facture;

import lombok.Data;

@Data
public class Facture {
    private Long id;
    private String fichierId;
    private String numeroFacture;
    private String lienFichier;
}
