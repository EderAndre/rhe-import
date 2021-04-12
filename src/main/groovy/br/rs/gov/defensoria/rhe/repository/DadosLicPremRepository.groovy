package br.rs.gov.defensoria.rhe.repository

import org.springframework.data.jpa.repository.JpaRepository
import br.rs.gov.defensoria.rhe.repository.entity.DadosLicPremEntity

interface DadosLicPremRepository extends JpaRepository<DadosLicPremEntity, Integer> {
}

