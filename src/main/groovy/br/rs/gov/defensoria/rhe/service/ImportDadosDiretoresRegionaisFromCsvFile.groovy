package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosDiretoresRegionaisEntity
import br.rs.gov.defensoria.rhe.repository.DadosDiretoresRegionaisRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosDiretoresRegionaisFromCsvFile {

    @Autowired
    DadosDiretoresRegionaisRepository dirReg

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        int numReg=0
        SimpleDateFormat  outputDate = new SimpleDateFormat("yyyy-MM-dd")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new DadosDiretoresRegionaisEntity(
                        NUMFUNCTIT :Integer.parseInt(it.NUMFUNCTITULAR),
                        NUMVINCTIT :Integer.parseInt(it.NUMVINCTITULAR),
                        DTINITIT :fileUtils.analyzeAndCorrectYearOfDate(it.DTINITIT),
                        DTFIMTIT :fileUtils.analyzeAndCorrectYearOfDate(it.DTFIMTIT),
                        MUNICIPIO:it.MUNICIPIO,
                        NUMFUNCSUBST :it.NUMFUNCSUBST?Integer.parseInt(it.NUMFUNCSUBST):null,
                        NUMVINCSUBST :it.NUMVINCSUBST?Integer.parseInt(it.NUMVINCSUBST):null,
                        DTINISUBST :fileUtils.analyzeAndCorrectYearOfDate(it.DTINISUBST),
                        DTFIMSUBST :fileUtils.analyzeAndCorrectYearOfDate(it.DTFIMSUBST),
                        IDENTIFICADOR :it.IDENTIFICADOR
                        )
                dirReg.save(data)
                numReg++
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
