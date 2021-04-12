package br.rs.gov.defensoria.rhe.repository.entity

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.IdClass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

import br.rs.gov.defensoria.rhe.repository.pk.FuncionalPK

@Entity
@Table(name = 'rhe_dados_vinculo')
@IdClass(value=FuncionalPK.class)
class DadosVinculoEntity {
    @Id
    @Column(name="NUMFUNC")
    Integer numfunc
    @Id
    @Column(name="NUMVINC")
    Integer numvinc

    @Column
    Date DTVAC

    @Column
    Date DTAPOSENT

    @Column
    String TIPOVINC

    @Column
    String REGIMEJUR

    @Column
    Date DTEXERC

    @Column
    Date DTPOSSE

    @Column
    Date DTNOM

    @Column
    Date DTESTABILIDADE
    
    @Column
    String MATRICULA

    @Column
    String BANCO

    @Column
    String AGENCIA

    @Column
    String CONTA

    @Column
    String MATRICULA1

    @Column
    String CLASSIFCONC

    @Column
    String REGPREV

    @Column
    String LIMITELC

    @Column
    String EMAILCORP
    
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
