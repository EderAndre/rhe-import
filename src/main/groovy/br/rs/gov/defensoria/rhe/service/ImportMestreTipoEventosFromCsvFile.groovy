package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.MestreTipoEventosEntity
import br.rs.gov.defensoria.rhe.repository.MestreTipoEventosRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportMestreTipoEventosFromCsvFile {

    @Autowired
    MestreTipoEventosRepository dadosTEv

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        try {
            dadosTEv.deleteAll()
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new MestreTipoEventosEntity(
                        TIPOEVENTO :it.TIPOEVENTO,
                        NOME :it.NOME,
                        NATUREZA :it.NATUREZA,
                        NATUREZA_PRINCIPAL :it.NATUREZA_PRINCIPAL,
                        TIPO_CARGO :it.TIPO_CARGO
                        )
                dadosTEv.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
