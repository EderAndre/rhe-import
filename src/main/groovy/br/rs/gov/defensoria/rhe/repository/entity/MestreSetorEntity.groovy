package br.rs.gov.defensoria.rhe.repository.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table
import javax.persistence.IdClass
import br.rs.gov.defensoria.rhe.repository.pk.SetorPK

@Entity
@Table(name = 'rhe_mestre_setor')
@IdClass(value=SetorPK.class)
class MestreSetorEntity {
    @Id
    @Column(name="EMP_CODIGO")
    String emp_codigo
    @Id
    @Column(name="SETOR")
    String setor

    @Column
    String PAISETOR

    @Column
    String NOMESETOR

    @Column
    String NOMESETORLONGO

    @Column
    Date DATAINI

    @Column
    String SETOR_CORP

    @Column
    String HLOCAL

    @Column
    String TIPOSETOR

    @Column
    String TIPOLOG

    @Column
    String NOMELOG

    @Column
    String NUMEROLOG

    @Column
    String COMPLEMENTO

    @Column
    String BAIRRO

    @Column
    String CEP

    @Column
    String CIDADE

    @Column
    String 	UF

    @Column
    String FONE

    @Column
    String FAX

    @Column
    String ORGANIZACAO

    @Column
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
