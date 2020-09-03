package com.hermes.poc.facturation.dao.jpa.facture;

import com.hermes.poc.facturation.dao.FactureDao;
import com.hermes.poc.facturation.exception.DaoError;
import com.hermes.poc.facturation.exception.DaoException;
import com.hermes.poc.facturation.facture.FactureMetadata;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FactureDaoAdapter implements FactureDao {


    private FactureRepository factureRepository;
    private FactureMetadataMapper factureMetadataMapper;


    @Override
    public FactureMetadata saveFactureMetadata(String numeroFacture, String fichierId) throws DaoException {
        try {
            FactureEntity factureEntity = factureMetadataMapper.toFactureEntity(numeroFacture, fichierId);
            factureEntity = factureRepository.save(factureEntity);
            return factureMetadataMapper.toFacture(factureEntity);
        } catch (Exception e) {
            throw new DaoException(DaoError.DB_ERROR, e);
        }
    }

    @Override
    public Optional<FactureMetadata> fetchFactureMetadata(Long id) throws DaoException {
        try {
            return factureRepository.findById(id).map(factureMetadataMapper::toFacture);
        } catch (Exception e) {
            throw new DaoException(DaoError.DB_ERROR, e);
        }
    }
}
