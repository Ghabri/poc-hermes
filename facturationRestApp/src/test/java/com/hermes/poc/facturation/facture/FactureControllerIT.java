package com.hermes.poc.facturation.facture;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class FactureControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_facture_nominal_case() throws Exception {
        // Given --

        // When --
        mockMvc.perform(
                get(String.format("/factures/%s", 100))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{\n" +
                        "   \"lienFichier\":\"https://test-poc-hermess-factures.s3.eu-west-1.amazonaws.com/facture-001.png\",\n" +
                        "   \"numeroFacture\":\"2020-001\"\n" +
                        "}", true));
        // Then --
    }

    @Test
    public void should_upload_facture_nominal_case() throws Exception {
        // Given --
        String fileName1 = "testFile1.txt";
        MockMultipartFile mockMultipartFile1 = new MockMultipartFile("file", fileName1, "text/plain", "helloWorld1".getBytes());

        // When --
        mockMvc.perform(
                MockMvcRequestBuilders.multipart("/factures")
                        .file(mockMultipartFile1)
                        .param("numero","2020-001")
        ).andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "   \"lienFichier\":\"https://test-poc-hermess-factures.s3.eu-west-1.amazonaws.com/facture-2020-001.pdf\",\n" +
                        "   \"numeroFacture\":\"2020-001\"\n" +
                        "}", true));
        // Then --
    }

}
