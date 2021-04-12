package br.rs.gov.defensoria.rhe.repository.entity

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table
import javax.persistence.IdClass

@Entity
@Table(name = 'rhe_mestre_cargos')
class MestreCargosEntity {
    @Id
    @Column(name="COD_CARGO_FUNCAO")
    int COD_CARGO_FUNCAO

    @Column(name="NOME_CARGO_FUNCAO")
    String NOME_CARGO_FUNCAO

    @Column(name="TIPO_CARGO")
    String TIPO_CARGO

    @Column(name="CARGO_FUNCAO")
    String CARGO_FUNCAO

    @Column(name="CATEGORIA")
    String CATEGORIA

    @Column(name="SUBCATEGORIA")
    String SUBCATEGORIA

    @Column(name="DT_EXTINCAO")
    Date DT_EXTINCAO
    
     @Column(updatable=false)
     Date CREATED
     
     @Column
     Date LASTUPDATED
  
    @PrePersist
    protected void onCreate() {
      CREATED = new Date()
    }
  
    @PreUpdate
    protected void onUpdate() {
      LASTUPDATED = new Date()
    }
}
