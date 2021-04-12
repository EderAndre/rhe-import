package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import javax.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosEventosEntity
import br.rs.gov.defensoria.rhe.repository.DadosEventosRepository
import br.rs.gov.defensoria.rhe.repository.entity.DadosEventosDeletedEntity
import br.rs.gov.defensoria.rhe.repository.DadosEventosDeletedRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosEventosDeletedFromCsvFile {

    @Autowired
    DadosEventosRepository dadosEv

    @Autowired
    DadosEventosDeletedRepository dadosEvDel
    
    @Autowired
    FileUtils fileUtils

    @Transactional
    public String importData(String fileName){
        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                if(it.OPERACAO=="E" || it.OPERACAO=="A"){
           
                    def registry=dadosEv.findOne(Integer.parseInt(it.NUMEV))
                    def status=registry?dadosEv.delete(registry):false
                  
                      def data=new DadosEventosDeletedEntity(
                        NUMEV :Integer.parseInt(it.NUMEV),
                        OPERACAO :it.OPERACAO,
                        TIPO :it.TIPO,
                        NUMFUNC :Integer.parseInt(it.NUMFUNC),
                        NUMVINC :Integer.parseInt(it.NUMVINC),
                        CARGO :Integer.parseInt(it.CARGO),
                        DTINI :it.DTINI?format.parse(it.DTINI):null,
                        DTFIM :it.DTFIM?format.parse(it.DTFIM):null,
                        MOTIVO :it.MOTIVO,
                        REFERENCIA :it.REFERENCIA,
                        STATUS_OLD: status?'EVENT_DELETED':'EVENT_NOT_FOUND'
                  
                       )
                     
                dadosEvDel.save(data)
                       
                }
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
