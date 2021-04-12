package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosFeriasPendEntity
import br.rs.gov.defensoria.rhe.repository.DadosFeriasPendRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosFeriasPendFromCsvFile {

    @Autowired
    DadosFeriasPendRepository dadosFeriasPend

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new DadosFeriasPendEntity(
                        NUMFUNC :Integer.parseInt(it.NUMFUNC),
                        NUMVINC :Integer.parseInt(it.NUMVINC),
                        DTINI :it.DTINI?format.parse(it.DTINI):null,
                        DTFIM :it.DTFIM?format.parse(it.DTFIM):null,
                        TOTALDIAS :it.TOTDIAS?Integer.parseInt(it.TOTDIAS):null,
                        DIAS_TIRADOS :it.DIAS_TIRADOS?Integer.parseInt(it.DIAS_TIRADOS):null,
                        DIAS_VENDIDOS :it.DIAS_VENDIDOS?Integer.parseInt(it.DIAS_VENDIDOS):null,
                        DIAS_CONVERTIDOS :it.DIAS_CONVERTIDOS?Integer.parseInt(it.DIAS_CONVERTIDOS):null,
                        SALDO :it.SALDO?Integer.parseInt(it.SALDO):null,
                        DT_PRESCRICAO :it.DT_PRESCRICAO?format.parse(it.DT_PRESCRICAO):null
                        )
                dadosFeriasPend.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
