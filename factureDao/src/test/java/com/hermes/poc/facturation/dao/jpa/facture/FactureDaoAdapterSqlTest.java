package com.hermes.poc.facturation.dao.jpa.facture;

import com.hermes.poc.facturation.exception.DaoException;
import com.hermes.poc.facturation.facture.FactureMetadata;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableAutoConfiguration
@ContextConfiguration(classes=FactureDaoAdapterSqlTest.Application.class)
class FactureDaoAdapterSqlTest {

    @Autowired
    private FactureDaoAdapter factureDaoAdapter;
    @Autowired
    private FactureRepository factureRepository;

    @Test
    public void should_add_facture() throws DaoException {
        // Given
        final String numeroFacture = "001";
        final String fichierId = "facture-001.pdf";

        // When
        FactureMetadata factureMetadata = factureDaoAdapter.saveFactureMetadata(numeroFacture, fichierId);

        // Then

        Assertions.assertAll(
                () -> assertEquals(1L, factureMetadata.getId()),
                () -> assertEquals(fichierId, factureMetadata.getFichierId()),
                () -> assertEquals(numeroFacture, factureMetadata.getNumeroFacture())
        );

        Optional<FactureEntity> factureEntity = factureRepository.findById(1L);

        Assertions.assertAll(
                () -> assertTrue(factureEntity.isPresent()),
                () -> assertEquals(fichierId, factureEntity.get().getFichierId()),
                () -> assertEquals(numeroFacture, factureEntity.get().getNumeroFacture())
        );

    }

    @Configuration
    @ComponentScan(basePackages = "com.hermes.poc.facturation.dao.jpa.facture")
    public static class Application {

    }
}