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

@Entity
@Table(name = 'rhe_dados_formacoes')
class DadosFormacoesEntity {

    @Id
    @GeneratedValue
    @Column(name="ROWID")
    int rowid

    @Column
    Integer NUMFUNC

    @Column
    String CURSOFORM

    @Column
    String HABILITACAO

    @Column
    String ESCOLA

    @Column
    Date DTINI

    @Column
    Date DTFIM

    @Column
    String CARGAHOR


    @Column
    String PTSCONTA

    @Column
    String VALIDPROMO

    @Column
    String PAGO

    @Column
    String PONTOLIB

    @Column
    String BOLSA

    @Column
    String DESCRICAO

    @Column
    Date DATA

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
