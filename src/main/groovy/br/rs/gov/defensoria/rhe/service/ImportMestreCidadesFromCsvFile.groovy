package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.MestreCidadesEntity
import br.rs.gov.defensoria.rhe.repository.MestreCidadesRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportMestreCidadesFromCsvFile {

    @Autowired
    MestreCidadesRepository dadosMCidades

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yy")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new MestreCidadesEntity(
                        ID_CIDADE :Integer.parseInt(it.ID_CIDADE),
                        NOME_CIDADE :it.NOME_CIDADE,
                        )
                dadosMCidades.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
