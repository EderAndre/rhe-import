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
@Table(name = 'rhe_dados_lic_premio_pend_historico')

class DadosLicPremPendEntity {
    @Id
    @GeneratedValue
    @Column(name="ROWID")
    Integer rowid

    @Column
    Integer NUMFUNC

    @Column
    Integer NUMVINC

    @Column
    Date DTINI

    @Column
    Date DTFIM

    @Column
    Integer TOTALDIAS
    
    @Column
    Integer DIAS_TIRADOS
    
    @Column
    Integer DIAS_VENDIDOS
    
    @Column
    Integer CONT_DOBRO
    
    @Column
    Integer SALDO
    
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
