package br.rs.gov.defensoria.rhe.repository

import org.springframework.data.jpa.repository.JpaRepository
import br.rs.gov.defensoria.rhe.repository.entity.MestreCargosEntity

interface MestreCargosRepository extends JpaRepository<MestreCargosEntity, Integer> {
}

