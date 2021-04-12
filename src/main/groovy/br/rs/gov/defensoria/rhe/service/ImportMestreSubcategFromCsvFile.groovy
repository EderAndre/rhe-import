package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.MestreSubcategEntity
import br.rs.gov.defensoria.rhe.repository.MestreSubcategRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportMestreSubcategFromCsvFile {

    @Autowired
    MestreSubcategRepository dadosSubcateg

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        try {
            dadosSubcateg.deleteAll()
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new MestreSubcategEntity(
                        CATEGORIA :it.CATEGORIA,
                        SIGLA :it.SIGLA
                        )
                dadosSubcateg.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
