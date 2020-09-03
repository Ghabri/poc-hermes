package com.hermes.poc.facturation.facture;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public interface FactureService {

    /**
     * Recupere la facture qui coresspond a l'id en paramete.
     *
     * @param id id facture dans la base de donnees
     *
     * @throws {@link com.hermes.poc.facturation.exception.ServiceException} si facture n'existe pas
     * @throws {@link com.hermes.poc.facturation.exception.ServiceException} si fichier facture introuvable
     */
    Facture retrieveFacture(Long id);

    /**
     * Ajoute la facture qui coresspond au numero en paramete et upload la facture au format fichier sur le filer
     *
     * @param numero numero de la facture
     * @param inputStream continu de la facture
     *
     */
    Facture addFacture(String numero, InputStream inputStream);
}
