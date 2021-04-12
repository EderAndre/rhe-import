package br.rs.gov.defensoria.rhe.repository.entity

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table
import javax.persistence.IdClass
import br.rs.gov.defensoria.rhe.repository.pk.CapacitacaoPK

@Entity
@Table(name = 'rhe_dados_capacitacoes')
@IdClass(value=CapacitacaoPK.class)
class DadosCapacitacaoEntity {

    @Id
    @Column
    Integer NUMFUNC
    @Id
    @Column
    Integer EVENTO

    @Column
    String NOME

    @Column
    Date DATA

    @Column
    String CARGAHORARIA

    @Column
    String ENTIDADE
    
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
