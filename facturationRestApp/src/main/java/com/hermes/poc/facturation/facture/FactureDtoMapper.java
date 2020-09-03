package com.hermes.poc.facturation.facture;

import org.springframework.stereotype.Service;

@Service
public class FactureDtoMapper {

    public FactureDto toFactureDto(Facture facture) {
        FactureDto factureDto = new FactureDto();
        factureDto.setLienFichier(facture.getLienFichier());
        factureDto.setNumeroFacture(facture.getNumeroFacture());
        return factureDto;
    }
}
