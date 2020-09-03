package com.hermes.poc.facturation.facture;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FactureMapper {

    public Facture toFacture(FactureMetadata factureMetadata, String factureUrl) {
        Facture facture = new Facture();
        facture.setId(factureMetadata.getId());
        facture.setNumeroFacture(factureMetadata.getNumeroFacture());
        facture.setFichierId(factureMetadata.getFichierId());
        facture.setLienFichier(factureUrl);
        return facture;
    }
}
