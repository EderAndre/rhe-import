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
@Table(name = 'rhe_mestre_trinomio')
class MestreTrinomiosEntity {
    @Id
    @GeneratedValue
    @Column(name="ROWID")
    int rowid

    @Column(name="TIPOVINC")
    String TIPOVINC

    @Column(name="CATEGORIA")
    String CATEGORIA

    @Column(name="REGIMEJUR")
    String REGIMEJUR
    
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
