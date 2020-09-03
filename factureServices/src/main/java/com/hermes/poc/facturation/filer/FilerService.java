package com.hermes.poc.facturation.filer;

import com.hermes.poc.facturation.exception.FilerException;

import java.io.InputStream;
import java.util.Optional;

public interface FilerService {

    String uploadFile(String fichierId, InputStream inputStream) throws FilerException;

    Optional<String> retrieveLienFichier(String fichierId) throws FilerException;
}
