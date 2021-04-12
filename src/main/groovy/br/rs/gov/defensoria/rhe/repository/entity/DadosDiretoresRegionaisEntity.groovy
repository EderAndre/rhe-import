package br.rs.gov.defensoria.rhe.repository.entity

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table

@Entity
@Table(name = 'rhe_dados_dir_reg')

class DadosDiretoresRegionaisEntity {
    @Id
    @GeneratedValue
    @Column(name="ROWID")
    int rowid

    @Column
    Integer NUMFUNCTIT

    @Column
    String NUMVINCTIT

    @Column
    Date DTINITIT

    @Column
    Date DTFIMTIT

    @Column
    String MUNICIPIO

    
    @Column
    Integer NUMFUNCSUBST

    @Column
    String NUMVINCSUBST

    @Column
    Date DTINISUBST

    @Column
    Date DTFIMSUBST
    
    @Column
    String IDENTIFICADOR
    
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
