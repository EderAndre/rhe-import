package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosFormacoesEntity
import br.rs.gov.defensoria.rhe.repository.DadosFormacoesRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosFormacoesFromCsvFile {

    @Autowired
    DadosFormacoesRepository dadosForm

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new DadosFormacoesEntity(
                        NUMFUNC :Integer.parseInt(it.NUMFUNC),
                        CURSOFORM :it.CURSOFORM,
                        HABILITACAO :it.HABILITACAO,
                        ESCOLA :it.ESCOLA,
                        DTINI :it.DTINI?format.parse(it.DTINI):null,
                        DTFIM :it.DTFIM?format.parse(it.DTFIM):null,
                        CARGAHOR :it.CARGAHOR,
                        PTSCONTA :it.PTSCONTA,
                        VALIDPROMO :it.VALIDPROMO,
                        PAGO :it.PAGO,
                        PONTOLIB :it.PONTOLIB,
                        BOLSA :it.BOLSA,
                        DESCRICAO :it.DESCRICAO,
                        DATA :it.DATA?format.parse(it.DATA):null,
                        OBS :it.OBS?it.OBS:null
                        )
                dadosForm.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
