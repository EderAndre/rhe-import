package br.rs.gov.defensoria.rhe.repository.entity

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table
import javax.persistence.GeneratedValue

@Entity
@Table(name = 'rhe_mestre_subcateg')
class MestreSubcategEntity {

    @Id
    @GeneratedValue
    @Column(name="ROWID")
    int rowid

    @Column(name="CATEGORIA")
    String CATEGORIA

    @Column(name="SIGLA")
    String SIGLA
    
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
