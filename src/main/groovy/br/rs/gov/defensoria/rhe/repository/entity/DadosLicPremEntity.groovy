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
@Table(name = 'rhe_dados_lic_premio_historico')

class DadosLicPremEntity {
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
    Date DTPREVFIM
    
    @Column
    Date DTFIM
    
    @Column
    Date DTINIPA

    @Column
    Date DTFIMPA
    
    @Column
    String ADIANTASAL

    @Column
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
