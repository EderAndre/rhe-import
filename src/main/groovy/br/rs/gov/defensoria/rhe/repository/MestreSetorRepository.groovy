package br.rs.gov.defensoria.rhe.repository

import org.springframework.data.jpa.repository.JpaRepository
import br.rs.gov.defensoria.rhe.repository.entity.MestreSetorEntity

interface MestreSetorRepository extends JpaRepository<MestreSetorEntity, Integer> {
}

