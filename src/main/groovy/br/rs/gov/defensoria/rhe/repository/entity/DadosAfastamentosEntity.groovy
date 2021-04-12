package br.rs.gov.defensoria.rhe.repository.entity

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table

@Entity
@Table(name = 'rhe_dados_afast')

class DadosAfastamentosEntity {
    @Id
    @Column
    Integer CHAVE

    @Column
    Integer NUMFUNC

    @Column
    String NUMVINC

    @Column
    Date DTINI

    @Column
    Date DTFIM

    @Column
    String MOTIVO

    @Column
    Date DTPREVFIM
    
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
