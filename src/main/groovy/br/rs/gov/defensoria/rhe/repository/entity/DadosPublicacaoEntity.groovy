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
@Table(name = 'rhe_dados_publicacao')

class DadosPublicacaoEntity {

    @Id
    @GeneratedValue
    @Column(name="ROWID")
    int rowid


    @Column
    Integer NUMFUNC

    @Column
    Integer NUMVINC


    @Column
    String TIPOEVENTO

    @Column
    String NUMPUBL

    @Column
    Date DATAPUBL

    @Column
    String TIPOPUBL

    @Column
    Date DATADO

    @Column
    String TIPODO

    @Column
    String AUTORIDADE

    @Column
    String NUMERO_PROCESSO

    @Column
    String MOTIVO

    @Column
    String OBS

    @Column
    String PUBLICACAO
    
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
