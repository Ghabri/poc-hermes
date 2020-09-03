package com.hermes.poc.facturation.dao.jpa.facture;

import com.hermes.poc.facturation.facture.FactureMetadata;
import org.springframework.stereotype.Service;

@Service
public class FactureMetadataMapper {


    public FactureMetadata toFacture(FactureEntity factureEntity) {
        FactureMetadata facture = new FactureMetadata();
        facture.setId(factureEntity.getId());
        facture.setFichierId(factureEntity.getFichierId());
        facture.setNumeroFacture(factureEntity.getNumeroFacture());
        return facture;
    }

    public FactureEntity toFactureEntity(String factureNumero, String  fichierId) {
        FactureEntity factureEntity = new FactureEntity();
        factureEntity.setFichierId(fichierId);
        factureEntity.setNumeroFacture(factureNumero);
        return factureEntity;
    }


}
