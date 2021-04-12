package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosEventosEntity
import br.rs.gov.defensoria.rhe.repository.DadosEventosRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosEventosFromCsvFile {

    @Autowired
    DadosEventosRepository dadosEv

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yy")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new DadosEventosEntity(
                        NUMEV :Integer.parseInt(it.NUMEV),
                        NUMFUNC :Integer.parseInt(it.NUMFUNC),
                        NUMVINC :Integer.parseInt(it.NUMVINC),
                        CARGO :it.CARGO,
                        DTINI :it.DTINI?format.parse(it.DTINI):null,
                        DTFIM :it.DTFIM?format.parse(it.DTFIM):null,
                        TIPOEVENTO :it.TIPOEVENTO,
                        REFERENCIA :it.REFERENCIA,
                        CARGO_FUNCAO :it.CARGO_FUNCAO,
                        SETOR :it.SETOR,
                        ESPECIEEVENTO :it.ESPECIEEVENTO,
                        FRACAO :it.FRACAO,
                        REPR :it.REPR,
                        PROVESP :it.PROVESP,
                        OBS :it.OBS
                        )
                dadosEv.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
