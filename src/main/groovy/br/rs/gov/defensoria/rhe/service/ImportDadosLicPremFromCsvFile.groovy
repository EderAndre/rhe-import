package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosLicPremEntity
import br.rs.gov.defensoria.rhe.repository.DadosLicPremRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosLicPremFromCsvFile {

    @Autowired
    DadosLicPremRepository dadosLicPrem

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new DadosLicPremEntity(
                        NUMFUNC :Integer.parseInt(it.NUMFUNC),
                        NUMVINC :Integer.parseInt(it.NUMVINC),
                        DTINI :it.DTINICIO?format.parse(it.DTINICIO):null,
                        DTPREVFIM :it.DTPREVTERMINO?format.parse(it.DTPREVTERMINO):null,
                        DTFIM :it.DTTERMINO?format.parse(it.DTTERMINO):null,
                        DTINIPA :it.DTINICIOPA?format.parse(it.DTINICIOPA):null,
                        DTFIMPA :it.DTFIMPA?format.parse(it.DTFIMPA):null,
                        ADIANTASAL :it.ADIANTASAL?it.ADIANTASAL:null,
                        OBS :it.OBS?it.OBS:null
                        )
                dadosLicPrem.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
