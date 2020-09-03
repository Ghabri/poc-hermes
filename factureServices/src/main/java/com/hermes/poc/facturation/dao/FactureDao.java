package com.hermes.poc.facturation.dao;

import com.hermes.poc.facturation.exception.DaoException;
import com.hermes.poc.facturation.facture.FactureMetadata;

import java.util.Optional;

public interface FactureDao {

    FactureMetadata saveFactureMetadata(String numeroFacture, String fichierId) throws DaoException;

    Optional<FactureMetadata> fetchFactureMetadata(Long id) throws DaoException;
}
