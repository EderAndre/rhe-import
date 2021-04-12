package br.rs.gov.defensoria.rhe.repository

import org.springframework.data.jpa.repository.JpaRepository
import br.rs.gov.defensoria.rhe.repository.entity.DadosFeriasEntity

interface DadosFeriasRepository extends JpaRepository<DadosFeriasEntity, Integer> {
}

