package br.rs.gov.defensoria.rhe.repository.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table
import javax.persistence.IdClass
import br.rs.gov.defensoria.rhe.repository.pk.SetorHistoricoPK

@Entity
@Table(name = 'rhe_mestre_setor_historico')

class MestreSetorHistoricoEntity {
    @Id
    @GeneratedValue
    @Column(name="ROWID")
    int rowid
    

    @Column(name="EMP_CODIGO")
    String emp_codigo

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
    
    @Column
    Date DATAFIM
    
    @Column
    boolean EXTINTO
    
    @Column
    String EMAIL
    
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
