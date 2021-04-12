package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosPublicacaoEntity
import br.rs.gov.defensoria.rhe.repository.DadosPublicacaoRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosPublicacaoFromCsvFile {

    @Autowired
    DadosPublicacaoRepository dadosPub

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        int numReg=0
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yy")
        SimpleDateFormat  outputDate = new SimpleDateFormat("yyyy-MM-dd")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new DadosPublicacaoEntity(
                        NUMFUNC :Integer.parseInt(it.NUMFUNC),
                        NUMVINC :Integer.parseInt(it.NUMVINC),
                        TIPOEVENTO :it.TIPOEVENTO,
                        NUMPUBL :it.NUMPUBL,
                        DATAPUBL :fileUtils.analyzeAndCorrectYearOfDate(it.DATAPUBL),
                        TIPOPUBL:it.TIPOPUBL,
                        DATADO :fileUtils.analyzeAndCorrectYearOfDate(it.DATADO),
                        TIPODO :it.TIPODO,
                        AUTORIDADE :it.AUTORIDADE,
                        NUMERO_PROCESSO :it.NUMERO_PROCESSO,
                        MOTIVO :it.MOTIVO,
                        OBS :it.OBS,
                        PUBLICACAO :it.PUBLICACAO
                        )
                dadosPub.save(data)
                numReg++
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
