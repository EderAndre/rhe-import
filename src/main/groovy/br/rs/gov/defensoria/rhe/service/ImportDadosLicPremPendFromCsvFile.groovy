package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosLicPremPendEntity
import br.rs.gov.defensoria.rhe.repository.DadosLicPremPendRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosLicPremPendFromCsvFile {

    @Autowired
    DadosLicPremPendRepository dadosLicPremPend

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new DadosLicPremPendEntity(
                        NUMFUNC :Integer.parseInt(it.NUMFUNC),
                        NUMVINC :Integer.parseInt(it.NUMVINC),
                        DTINI :it.DTINI?format.parse(it.DTINI):null,
                        DTFIM :it.DTFIM?format.parse(it.DTFIM):null,
                        TOTALDIAS :it.DIAS_DIREITO?Integer.parseInt(it.DIAS_DIREITO):null,
                        DIAS_TIRADOS :it.GOZO?Integer.parseInt(it.GOZO):null,
                        DIAS_VENDIDOS :it.VENDA?Integer.parseInt(it.VENDA):null,
                        CONT_DOBRO :it.CONT_DOBRO?Integer.parseInt(it.CONT_DOBRO):null,
                        SALDO :it.HAVER?Integer.parseInt(it.HAVER):null
                        )
                dadosLicPremPend.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
