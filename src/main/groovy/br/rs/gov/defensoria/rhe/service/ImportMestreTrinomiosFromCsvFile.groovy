package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.MestreTrinomiosEntity
import br.rs.gov.defensoria.rhe.repository.MestreTrinomiosRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportMestreTrinomiosFromCsvFile {

    @Autowired
    MestreTrinomiosRepository dadosTrin

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        try {
            dadosTrin.deleteAll()
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new MestreTrinomiosEntity(
                        TIPOVINC :it.TIPOVINC,
                        CATEGORIA :it.CATEGORIA,
                        REGIMEJUR :it.REGIMEJUR
                        )
                dadosTrin.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
