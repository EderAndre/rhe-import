package br.rs.gov.defensoria.rhe.repository.entity

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table

@Entity
@Table(name = 'rhe_dados_evento')

class DadosEventosEntity {
    @Id
    @Column
    Integer NUMEV

    @Column
    Integer NUMFUNC

    @Column
    String NUMVINC

    @Column
    String CARGO

    @Column
    Date DTINI

    @Column
    Date DTFIM

    @Column
    String TIPOEVENTO

    @Column
    String REFERENCIA

    @Column
    String CARGO_FUNCAO

    @Column
    String SETOR

    @Column
    String ESPECIEEVENTO

    @Column
    String FRACAO

    @Column
    String REPR

    @Column
    String PROVESP

    @Column(columnDefinition = "TEXT")
    String OBS
    
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
