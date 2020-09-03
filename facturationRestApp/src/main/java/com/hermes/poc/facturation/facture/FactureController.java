package com.hermes.poc.facturation.facture;

import com.hermes.poc.facturation.exception.ErrorCatalog;
import com.hermes.poc.facturation.exception.ErrorOrigin;
import com.hermes.poc.facturation.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@Slf4j
@RestController
@RequestMapping("factures")
@AllArgsConstructor
public class FactureController {

    private FactureService factureService;
    private FactureDtoMapper factureDtoMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FactureDto addFacture(@RequestParam("file") MultipartFile file,
                                 @RequestParam(name = "numero") String numero) {
        try (InputStream inputStream = file.getInputStream()) {
            Facture facture = factureService.addFacture(numero, inputStream);
            return factureDtoMapper.toFactureDto(facture);
        } catch (IOException e) {
            log.error("Erreur lors de recuperation du inputstream", e);
            throw new ServiceException(ErrorCatalog.APPLICATION_ERROR, ErrorOrigin.SYSTEM, "Erreur lors de recuperation du inputstream", e);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FactureDto retrieveFacture(@PathVariable Long id) {
        Facture facture = factureService.retrieveFacture(id);
        return factureDtoMapper.toFactureDto(facture);
    }


}
