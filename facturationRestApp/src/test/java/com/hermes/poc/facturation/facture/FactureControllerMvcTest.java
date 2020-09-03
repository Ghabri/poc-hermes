package com.hermes.poc.facturation.facture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FactureController.class)
class FactureControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FactureService factureService;
    @MockBean
    private FactureDtoMapper factureDtoMapper;

    @Test
    public void should_return_facture_nominal_case() throws Exception {
        // Given --
        FactureDto expectedFactureDto = new FactureDto();
        expectedFactureDto.setNumeroFacture("2020-001");
        expectedFactureDto.setLienFichier("https://s3.amazonaws.com/hermess-factures/facture-2020-001.pdf");

        Facture facture = new Facture();
        when(factureService.retrieveFacture(1L)).thenReturn(facture);
        when(factureDtoMapper.toFactureDto(facture)).thenReturn(expectedFactureDto);

        // When --
        mockMvc.perform(
                get(String.format("/factures/%s", 1))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{\n" +
                        "   \"lienFichier\":\"https://s3.amazonaws.com/hermess-factures/facture-2020-001.pdf\",\n" +
                        "   \"numeroFacture\":\"2020-001\"\n" +
                        "}", true));
        // Then --
    }

}