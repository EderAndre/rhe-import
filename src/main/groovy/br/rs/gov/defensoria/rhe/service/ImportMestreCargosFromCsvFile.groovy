package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.MestreCargosEntity
import br.rs.gov.defensoria.rhe.repository.MestreCargosRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportMestreCargosFromCsvFile {

    @Autowired
    MestreCargosRepository dadosMCargos

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new MestreCargosEntity(
                        COD_CARGO_FUNCAO :Integer.parseInt(it.COD_CARGO_FUNCAO),
                        NOME_CARGO_FUNCAO :it.NOME_CARGO_FUNCAO,
                        TIPO_CARGO :it.TIPO_CARGO,
                        CARGO_FUNCAO :it.CARGO_FUNCAO,
                        CATEGORIA :it.CATEGORIA,
                        SUBCATEGORIA :it.SUBCATEGORIA,
                        DT_EXTINCAO :it.DT_EXTINCAO?format.parse(it.DT_EXTINCAO):null
                        )
                dadosMCargos.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
