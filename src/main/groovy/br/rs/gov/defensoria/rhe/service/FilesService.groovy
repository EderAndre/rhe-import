package br.rs.gov.defensoria.rhe.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import br.rs.gov.defensoria.rhe.utils.UriDiscovery
import br.rs.gov.defensoria.rhe.utils.FileUtils
import br.rs.gov.defensoria.rhe.utils.Type
import br.rs.gov.defensoria.rhe.utils.TypesDefinition

@Component
class FilesService {
    TypesDefinition type=new TypesDefinition()

    def public detectType(String fileName){
        def typeSelected=null
        switch(fileName){
            case ~/.*$type.AFASTAMENTO.expressionFromDetection.*/:typeSelected=type.AFASTAMENTO;break
            case ~/.*$type.DIRETORES_REGIONAIS.expressionFromDetection.*/:typeSelected=type.DIRETORES_REGIONAIS;break
            case ~/.*$type.CARGOS.expressionFromDetection.*/:typeSelected=type.CARGOS;break
            case ~/.*$type.DADOS_PUBLICACAO.expressionFromDetection.*/:typeSelected=type.DADOS_PUBLICACAO;break
            case ~/.*$type.EVENTO.expressionFromDetection.*/:typeSelected=type.EVENTO;break
            case ~/.*$type.FUNCIONAL.expressionFromDetection.*/:typeSelected=type.FUNCIONAL;break
            case ~/.*$type.MUNICIPIOS.expressionFromDetection.*/:typeSelected=type.MUNICIPIOS;break
            case ~/.*$type.PAIS.expressionFromDetection.*/:typeSelected=type.PAIS;break
            case ~/.*$type.SETOR.expressionFromDetection.*/:typeSelected=type.SETOR;break
            case ~/.*$type.SETOR_HISTORICO.expressionFromDetection.*/:typeSelected=type.SETOR_HISTORICO;break
            case ~/.*$type.SUBCATEGORIA.expressionFromDetection.*/:typeSelected=type.SUBCATEGORIA;break
            case ~/.*$type.TIPOEVENTOS.expressionFromDetection.*/:typeSelected=type.TIPOEVENTOS;break
            case ~/.*$type.TRINOMIOS.expressionFromDetection.*/:typeSelected=type.TRINOMIOS;break
            case ~/.*$type.VINCULO.expressionFromDetection.*/:typeSelected=type.VINCULO;break
            case ~/.*$type.FERIAS.expressionFromDetection.*/:typeSelected=type.FERIAS;break
            case ~/.*$type.FERIAS_PENDENTES.expressionFromDetection.*/:typeSelected=type.FERIAS_PENDENTES;break
            case ~/.*$type.LICENCA_PREMIO.expressionFromDetection.*/:typeSelected=type.LICENCA_PREMIO;break
            case ~/.*$type.LICENCA_PREMIO_PENDENTE.expressionFromDetection.*/:typeSelected=type.LICENCA_PREMIO_PENDENTE;break
            case ~/.*$type.FERIAS_FULL.expressionFromDetection.*/:typeSelected=type.FERIAS_FULL;break
            case ~/.*$type.CAPACITACAO.expressionFromDetection.*/:typeSelected=type.CAPACITACAO;break
            case ~/.*$type.EVENT_DELETED.expressionFromDetection.*/:typeSelected=type.EVENT_DELETED;break
            case ~/.*$type.FORMACOES.expressionFromDetection.*/:typeSelected=type.FORMACOES;break
      
        }
        return typeSelected
    }
    def public generateFileNormalized(String fileOrigin, String fileDestiny, def typeSelected){
        def res=false
        if(typeSelected.extension=="TXT") generateCsvFromTxtNormalized(fileOrigin, fileDestiny, typeSelected)
        else if(typeSelected.extension=="CSV") generateCsvFromCsvNormalized(fileOrigin, fileDestiny)
    }

    def public generateCsvFromTxtNormalized(String fileOrigin, String fileDestiny, def typeSelected){
        fileDestiny=fileDestiny.replace(".TXT","_NORMALIZED.CSV")
        def res=new FileUtils().normalizeFileTxt(
                fileOrigin,
                typeSelected.columnLimits.toList(),
                typeSelected.columnLabels.toList()
                )
        return new FileUtils().createFile(fileDestiny,res)
    }

    def public generateCsvFromCsvNormalized(String fileOrigin, String fileDestiny){
        fileDestiny=fileDestiny.replace(".CSV","_NORMALIZED.CSV")
        def res=new FileUtils().normalizeFileCsv(fileOrigin)
        return new FileUtils().createFile(fileDestiny,res)
    }
}
