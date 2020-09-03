package com.hermes.poc.facturation.filer;

import com.hermes.poc.facturation.aws.s3.S3Service;
import com.hermes.poc.facturation.exception.FilerError;
import com.hermes.poc.facturation.exception.FilerException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FilerServiceAdapter implements FilerService {

    private S3Service s3Service;


    @Override
    public String uploadFile(String fichierId, InputStream inputStream) throws FilerException {
        try {
            s3Service.uploadFile(fichierId, inputStream).getMetadata();
            return s3Service.geturl(fichierId).toExternalForm();
        } catch (Exception e) {
            throw new FilerException(FilerError.FILER_ERROR, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new FilerException(FilerError.IO_ERROR, e);
            }
        }
    }

    @Override
    public Optional<String> retrieveLienFichier(String fichierId) throws FilerException {
        try {
            if (!s3Service.exists(fichierId)) {
                return Optional.empty();
            }
            String url = s3Service.geturl(fichierId).toExternalForm();
            log.debug("Url fichier {} est: [{}]", fichierId, url);
            return Optional.of(url);
        } catch (Exception e) {
            throw new FilerException(FilerError.FILER_ERROR, e);
        }
    }
}

