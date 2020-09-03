package com.hermes.poc.facturation.dao.jpa.facture;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "factures")
public class FactureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_facture")
    @NotNull(message = "Numero facture est obligatoire")
    private String numeroFacture;

    @Column(name = "fichier_id")
    @NotNull(message = "Numero fichier facture est obligatoire")
    private String fichierId;

}
