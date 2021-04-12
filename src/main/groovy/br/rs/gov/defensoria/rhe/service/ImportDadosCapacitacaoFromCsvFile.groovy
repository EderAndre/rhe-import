package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosCapacitacaoEntity
import br.rs.gov.defensoria.rhe.repository.DadosCapacitacaoRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosCapacitacaoFromCsvFile {

    @Autowired
    DadosCapacitacaoRepository dadosCap

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new DadosCapacitacaoEntity(
                        NUMFUNC :Integer.parseInt(it.NUMFUNC),
                        EVENTO :Integer.parseInt(it.EVENTO),
                        NOME :it.NOME,
                        DATA :it.DATA?format.parse(it.DATA):null,
                        CARGAHORARIA :it.CARGAHORARIA,
                        ENTIDADE :it.ENTIDADE,
                        OBS :it.OBS?it.OBS:null
                        )
                dadosCap.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
