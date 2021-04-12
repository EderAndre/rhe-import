package br.rs.gov.defensoria.rhe.repository.entity

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table

@Entity
@Table(name = 'rhe_dados_evento_deleted')

class DadosEventosDeletedEntity {
    @Id
    @GeneratedValue
    @Column(name="ROWID")
    int rowid
    
    @Column
    Integer  NUMEV
    
    @Column
    String OPERACAO 

    @Column
    String TIPO

    @Column
    Integer NUMFUNC

    @Column
    Integer NUMVINC

    @Column
    Integer CARGO

    @Column
    Date DTINI

    @Column
    Date DTFIM

    @Column
    String MOTIVO

    @Column
    String REFERENCIA
    
    @Column
    String STATUS_OLD
    
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
