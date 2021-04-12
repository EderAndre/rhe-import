package br.rs.gov.defensoria.rhe.repository

import org.springframework.data.jpa.repository.JpaRepository
import br.rs.gov.defensoria.rhe.repository.entity.MestreTrinomiosEntity

interface MestreTrinomiosRepository extends JpaRepository<MestreTrinomiosEntity, Integer> {
}

