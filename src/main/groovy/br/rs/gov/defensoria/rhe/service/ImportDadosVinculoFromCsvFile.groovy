package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosVinculoEntity
import br.rs.gov.defensoria.rhe.repository.DadosVinculoRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosVinculoFromCsvFile {

    @Autowired
    DadosVinculoRepository dadosVinc

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yy")
        SimpleDateFormat  format2 = new SimpleDateFormat("dd/MM/yyyy")

        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                def data=new DadosVinculoEntity(
                        numfunc :Integer.parseInt(it.NUMFUNC),
                        numvinc :Integer.parseInt(it.NUMVINC),
                        DTVAC :it.DTVAC?format.parse(it.DTVAC):null,
                        DTAPOSENT :it.DTAPOSENT?format.parse(it.DTAPOSENT):null,
                        TIPOVINC :it.TIPOVINC,
                        REGIMEJUR :it.REGIMEJUR,
                        DTEXERC :it.DTEXERC?format.parse(it.DTEXERC):null,
                        DTPOSSE :it.DTPOSSE?format.parse(it.DTPOSSE):null,
                        DTNOM :it.DTNOM?format.parse(it.DTNOM):null,
                        DTESTABILIDADE :it.DTESTABILIDADE?format2.parse(it.DTESTABILIDADE):null,
                        MATRICULA :it.MATRICULA,
                        BANCO :it.BANCO,
                        AGENCIA:it.AGENCIA,
                        CONTA :it.CONTA,
                        MATRICULA1 :it.MATRICULA1,
                        CLASSIFCONC :it.CLASSIFCONC,
                        REGPREV :it.REGPREV,
                        LIMITELC :it.LIMITELC,
                        EMAILCORP :it.EMAILCORP
                        )
                dadosVinc.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
