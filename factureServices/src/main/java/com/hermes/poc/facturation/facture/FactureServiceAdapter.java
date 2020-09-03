package com.hermes.poc.facturation.facture;

import com.hermes.poc.facturation.dao.FactureDao;
import com.hermes.poc.facturation.exception.DaoException;
import com.hermes.poc.facturation.exception.ErrorCatalog;
import com.hermes.poc.facturation.exception.FilerException;
import com.hermes.poc.facturation.exception.ServiceException;
import com.hermes.poc.facturation.filer.FilerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

import static com.hermes.poc.facturation.exception.ErrorCatalog.DB_ERROR;
import static com.hermes.poc.facturation.exception.ErrorCatalog.FILER_ERROR;
import static com.hermes.poc.facturation.exception.ErrorOrigin.*;

@Slf4j
@Service
@AllArgsConstructor
public class FactureServiceAdapter implements FactureService {

    private FactureDao factureDao;
    private FilerService filerService;
    private FactureMapper factureMapper;


    @Override
    public Facture retrieveFacture(Long id) {
        String fichierId = null;
        try {
            FactureMetadata factureMetadata = fetchFactureMetadata(id);
            fichierId = factureMetadata.getFichierId();
            String factureUrl = retrieveFactureUrl(id, fichierId);
            return factureMapper.toFacture(factureMetadata, factureUrl);
        } catch (ServiceException e) {
            throw e;
        } catch (DaoException e) {
            log.error("Erreur BD durant fetch facture avec l'id: [{}]", id, e);
            throw new ServiceException(DB_ERROR, DB, e);
        } catch (FilerException e) {
            log.error("Erreur filer durant retrieve fichier dont la cle est: [{}]", fichierId, e);
            throw new ServiceException(FILER_ERROR, FILER, e);
        }
    }

    @Override
    public Facture addFacture(String numeroFacture, InputStream inputStream) {
        try {
            String fichierId = String.format("facture-%s.pdf", numeroFacture);
            FactureMetadata factureMetadata = factureDao.saveFactureMetadata(numeroFacture, fichierId);
            final String factureUrl = filerService.uploadFile(fichierId, inputStream);
            return factureMapper.toFacture(factureMetadata, factureUrl);
        } catch (DaoException e) {
            log.error("Erreur BD durant ajout facture numero: [{}]", numeroFacture, e);
            throw new ServiceException(DB_ERROR, DB, e);
        } catch (FilerException e) {
            log.error("Erreur filer durant upload facture [{}] to s3", numeroFacture, e);
            throw new ServiceException(FILER_ERROR, FILER, e);
        }
    }

    private String retrieveFactureUrl(Long id, String fichierId) throws FilerException {
        return filerService.retrieveLienFichier(fichierId)
                .orElseThrow(() -> {
                    String message = String.format("Le fichier de la facture %s est introuvable", id);
                    log.error(message);
                    return new ServiceException(ErrorCatalog.ILLEGAL_STATE, SYSTEM, message);
                });
    }

    private FactureMetadata fetchFactureMetadata(Long id) throws DaoException {
        return factureDao.fetchFactureMetadata(id)
                .orElseThrow(() -> {
                    String message = String.format("Les metadatas de la facture %s est introuvables", id);
                    log.error(message);
                    return new ServiceException(ErrorCatalog.RESOURCE_INTROUVABLE, CLIENT, message);
                });
    }
}
