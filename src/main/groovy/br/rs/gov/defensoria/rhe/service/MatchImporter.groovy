package br.rs.gov.defensoria.rhe.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import br.rs.gov.defensoria.rhe.utils.TypesDefinition

@Service
class MatchImporter {

    @Autowired
    ImportDadosAfastamentosFromCsvFile afs

    @Autowired
    ImportDadosDiretoresRegionaisFromCsvFile dirReg

    @Autowired
    ImportMestreCargosFromCsvFile cargos

    @Autowired
    ImportDadosPublicacaoFromCsvFile dadosPub

    @Autowired
    ImportDadosEventosFromCsvFile eventos

    @Autowired
    ImportDadosFuncionaisFromCsvFile dadosFunc

    @Autowired
    ImportMestreCidadesFromCsvFile municipios

    @Autowired
    ImportMestrePaisFromCsvFile paises

    @Autowired
    ImportMestreSetorFromCsvFile setor

    @Autowired
    ImportMestreSetorHistoricoFromCsvFile setorHist

    @Autowired
    ImportMestreSubcategFromCsvFile subCat

    @Autowired
    ImportMestreTipoEventosFromCsvFile tpEventos

    @Autowired
    ImportMestreTrinomiosFromCsvFile trinomios

    @Autowired
    ImportDadosVinculoFromCsvFile vinculos

    @Autowired
    ImportDadosFeriasFromCsvFile ferias

    @Autowired
    ImportDadosFeriasPendFromCsvFile feriasPend

    @Autowired
    ImportDadosLicPremFromCsvFile licPrem

    @Autowired
    ImportDadosLicPremPendFromCsvFile licPremPend

    @Autowired
    ImportDadosFeriasFullFromCsvFile feriasFull

    @Autowired
    ImportDadosCapacitacaoFromCsvFile capacitacao

    @Autowired
    ImportDadosEventosDeletedFromCsvFile eventDeleted

    @Autowired
    ImportDadosFormacoesFromCsvFile formacoes

    @Autowired
    FilesService files

    @Autowired
    TypesDefinition type

    def public matchFile(String fileName){
        return new FilesService().detectType(fileName)
    }

    def public dispatchFile(String fileName){
        def res
        def match=matchFile(fileName)
        def importer
        switch(match){
            case type.AFASTAMENTO:importer=afs;break
            case type.DIRETORES_REGIONAIS:importer=dirReg;break
            case type.CARGOS:importer=cargos;break
            case type.DADOS_PUBLICACAO:importer=dadosPub;break
            case type.EVENTO:importer=eventos;break
            case type.FUNCIONAL:importer=dadosFunc;break
            case type.MUNICIPIOS:importer=municipios;break
            case type.PAIS:importer=paises;break
            //case type.SETOR:importer=setor;break
            case type.SETOR_HISTORICO:importer=setorHist;break
            case type.SUBCATEGORIA:importer=subCat;break
            case type.TIPOEVENTOS:importer=tpEventos;break
            case type.TRINOMIOS:importer=trinomios;break
            case type.VINCULO:importer=vinculos;break
            case type.FERIAS:importer=ferias;break
            case type.FERIAS_PENDENTES:importer=feriasPend;break
            case type.LICENCA_PREMIO:importer=licPrem;break
            case type.LICENCA_PREMIO_PENDENTE:importer=licPremPend;break
            case type.FERIAS_FULL:importer=feriasFull;break
            case type.CAPACITACAO:importer=capacitacao;break
            case type.EVENT_DELETED:importer=eventDeleted;break
            case type.FORMACOES:importer=formacoes;break
        }
        res= importer.importData(fileName)

        return res
    }
}
