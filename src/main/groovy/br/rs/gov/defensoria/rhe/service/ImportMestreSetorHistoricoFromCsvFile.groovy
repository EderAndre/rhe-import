package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.MestreSetorHistoricoEntity
import br.rs.gov.defensoria.rhe.repository.MestreSetorHistoricoRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportMestreSetorHistoricoFromCsvFile {

    @Autowired
    MestreSetorHistoricoRepository dadosSetor

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message

        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new MestreSetorHistoricoEntity(
                        emp_codigo :it.EMP_CODIGO,
                        setor :it.SETOR,
                        PAISETOR :it.PAISETOR,
                        NOMESETOR :it.NOMESETOR,
                        NOMESETORLONGO :it.NOMESETORLONGO,
                        DATAINI :it.DATAINI?fileUtils.analyzeAndCorrectYearOfDate(it.DATAINI):null,
                        SETOR_CORP:it.SETOR_CORP,
                        HLOCAL :it.HLOCAL,
                        TIPOSETOR :it.TIPOSETOR,
                        TIPOLOG :it.TIPOLOG,
                        NOMELOG :it.NOMELOG,
                        NUMEROLOG :it.NUMEROLOG,
                        COMPLEMENTO :it.COMPLEMENTO,
                        BAIRRO:it.BAIRRO,
                        CEP :it.CEP,
                        CIDADE :it.CIDADE,
                        UF :it.UF,
                        FONE :it.FONE,
                        FAX :it.FAX,
                        ORGANIZACAO :it.ORGANIZACAO,
                        SIGLA :it.SIGLA,
                        DATAFIM :it.DATAFIM?fileUtils.analyzeAndCorrectYearOfDate(it.DATAFIM):null,
                        EXTINTO :it.EXTINTO=='S'?true:false,
                        EMAIL :it.EMAIL
                        )
                dadosSetor.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
