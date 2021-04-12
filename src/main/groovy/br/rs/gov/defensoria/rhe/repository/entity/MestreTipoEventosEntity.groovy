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
@Table(name = 'rhe_mestre_tipoevento')
class MestreTipoEventosEntity {

    @Id
    @GeneratedValue
    @Column(name="ROWID")
    int rowid

    @Column(name="TIPOEVENTO")
    String TIPOEVENTO

    @Column(name="NOME")
    String NOME

    @Column(name="NATUREZA")
    String NATUREZA

    @Column(name="NATUREZA_PRINCIPAL")
    String NATUREZA_PRINCIPAL

    @Column(name="TIPO_CARGO")
    String TIPO_CARGO
    
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
