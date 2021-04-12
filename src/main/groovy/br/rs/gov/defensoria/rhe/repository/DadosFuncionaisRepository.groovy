package br.rs.gov.defensoria.rhe.repository

import org.springframework.data.jpa.repository.JpaRepository
import br.rs.gov.defensoria.rhe.repository.entity.DadosFuncionaisEntity

interface DadosFuncionaisRepository extends JpaRepository<DadosFuncionaisEntity, Integer> {
    List<DadosFuncionaisEntity> findByNumfunc(int numfunc)
}

