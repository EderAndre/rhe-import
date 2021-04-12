package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosFeriasFullEntity
import br.rs.gov.defensoria.rhe.repository.DadosFeriasFullRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosFeriasFullFromCsvFile {

    @Autowired
    DadosFeriasFullRepository dadosFeriasFull

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy")
        try {
            dadosFeriasFull.deleteAll();
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new DadosFeriasFullEntity(
                        NUMFUNC :Integer.parseInt(it.NUMFUNC),
                        NUMVINC :Integer.parseInt(it.NUMVINC),
                        DTINI :it.DTINI?format.parse(it.DTINI):null,
                        DTFIM :it.DTFIM?format.parse(it.DTFIM):null,
                        MESANOPAGTO :it.MES_ANO_FOLHA,
                        NRODIASPAGTO :it.NRDIASPAGTO,
                        DTINIPA :it.INICIOPA?format.parse(it.INICIOPA):null,
                        DTFIMPA :it.FIMPA?format.parse(it.FIMPA):null,
                        TOTALDIAS :it.TOTALDIAS?Integer.parseInt(it.TOTALDIAS):null,
                        DIASVENDIDOS :it.DIASVENDIDOS?Integer.parseInt(it.DIASVENDIDOS):null,
                        DIASGOZADOS :it.DIASGOZADOS?Integer.parseInt(it.DIASGOZADOS):null,
                        SALDO :it.SALDO?Integer.parseInt(it.SALDO):null,
                        OBS :it.OBS?it.OBS:null
                        )
                dadosFeriasFull.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
