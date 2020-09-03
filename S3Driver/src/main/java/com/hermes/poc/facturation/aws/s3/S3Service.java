package com.hermes.poc.facturation.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;

@Component
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${facturation.filer.bucket.name}")
    private String buckctName;


    public PutObjectResult uploadFile(String fileName, InputStream inputStream) {
        return amazonS3.putObject(buckctName, fileName, inputStream, null);
    }

    public URL geturl(String fileKey) {
        return amazonS3.getUrl(buckctName, fileKey);
    }

    public boolean exists(String fileKey) {
        return amazonS3.doesObjectExist(buckctName, fileKey);
    }

}
