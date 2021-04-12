package br.rs.gov.defensoria.rhe.repository.entity

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table
import javax.persistence.IdClass
import br.rs.gov.defensoria.rhe.repository.pk.FuncionalPK

@Entity
@Table(name = 'rhe_dados_funcional')
@IdClass(value=FuncionalPK.class)
class DadosFuncionaisEntity {
    @Id
    @Column(name="NUMFUNC")
    Integer numfunc
    @Id
    @Column(name="NUMVINC")
    Integer numvinc

    @Column
    String CPF

    @Column
    String NOME

    @Column
    String NUMRG

    @Column
    String UFRG

    @Column
    String SEXO

    @Column
    Date DTNASC

    @Column
    String PAI

    @Column
    String MAE

    @Column
    String ESTCIVIL

    @Column
    String ESCOLARIDADE

    @Column
    String SETOR

    @Column
    String SETOR_LOTACAO

    @Column
    String NOMELOGENDER

    @Column
    String TIPOLOGENDER

    @Column
    String 	NUMENDER

    @Column
    String BAIRROENDER

    @Column
    String CEPENDER

    @Column
    String COMPLENDER

    @Column
    String UFENDER

    @Column
    String CIDADEENDER

    @Column
    String TELEFONE

    @Column
    String NUMTEL_CELULAR

    @Column
    String NACIONALIDADE

    @Column
    String DTVAC

    @Column
    String DTAPOSENT

    @Column
    String TIPOVINC

    @Column
    String REGIMEJUR

    @Column
    String DTEXERC

    @Column
    String DTPOSSE

    @Column
    String DTNOM

    @Column
    String MATRICULA

    @Column
    String E_MAIL

    @Column
    String BANCO

    @Column
    String AGENCIA

    @Column
    String CONTA

    @Column
    String PISPASEP

    @Column
    String COD_CIDNASC

    @Column
    String COD_CIDENDER

    @Column
    String DTINI_CESSAO

    @Column
    String DTAFAST

    @Column
    String SIT_RHE

    @Column
    String G_SANGUINEO

    @Column
    String RACA

    @Column
    String DEFICIENTE

    @Column
    String TIPODEFIC

    @Column
    String OBSERV

    @Column
    String ORGAORG

    @Column
    String EXPEDRG

    @Column
    String NUMTITEL

    @Column
    String ZONATITEL

    @Column
    String SECTITEL

    @Column
    String UFTITEL

    @Column
    String NUMDOCMILI

    @Column
    String DOCMILITAR

    @Column
    String SERDOCMILI

    @Column
    String CATMILI

    @Column
    String UFDOCMILI

    @Column
    String FORCA

    @Column
    String CNH

    @Column
    String CATCNH

    @Column
    String ORGEXPCNH

    @Column
    String DATAEXPCNH

    @Column
    String UFCNH

    @Column
    String VALIDCNH

    @Column
    String IDENTPROF

    @Column
    String TIPOIDPROF

    @Column
    String UF_IDENTPROF

    @Column
    String DTEXPIDENTPROF
    
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
