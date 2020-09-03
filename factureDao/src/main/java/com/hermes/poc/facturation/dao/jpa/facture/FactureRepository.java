package com.hermes.poc.facturation.dao.jpa.facture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends JpaRepository<FactureEntity, Long> {

}
