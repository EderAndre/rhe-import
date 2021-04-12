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
@Table(name = 'rhe_dados_ferias_historico')

class DadosFeriasEntity {
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
    String MESANOPAGTO

    @Column
    String NRODIASPAGTO
    
    @Column
    Date DTINIPA

    @Column
    Date DTFIMPA

    @Column
    Integer TOTALDIAS
    
    @Column
    Integer DIASVENDIDOS
    
    @Column
    Integer DIASGOZADOS
    
    @Column
    Integer SALDO

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
