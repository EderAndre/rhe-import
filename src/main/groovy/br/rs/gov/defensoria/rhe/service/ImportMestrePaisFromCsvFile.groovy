package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.MestrePaisEntity
import br.rs.gov.defensoria.rhe.repository.MestrePaisRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportMestrePaisFromCsvFile {

    @Autowired
    MestrePaisRepository dadosMPais

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new MestrePaisEntity(
                        ID_PAIS :Integer.parseInt(it.ID_PAIS),
                        NACIONALIDADE :it.NACIONALIDADE,
                        )
                dadosMPais.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
