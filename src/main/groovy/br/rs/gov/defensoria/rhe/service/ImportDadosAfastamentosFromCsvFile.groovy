package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import javax.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosAfastamentosEntity
import br.rs.gov.defensoria.rhe.repository.DadosAfastamentosRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosAfastamentosFromCsvFile {

    @Autowired
    DadosAfastamentosRepository dadosAf

    @Autowired
    FileUtils fileUtils

    @Transactional
    public String importData(String fileName){
        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                if(it.OPERACAO=="E"){
                    def registry=dadosAf.findOne(Integer.parseInt(it.CHAVE))
                    registry?dadosAf.delete(registry):false
                }
                else{
                    def data=new DadosAfastamentosEntity(
                            CHAVE :Integer.parseInt(it.CHAVE),
                            NUMFUNC :Integer.parseInt(it.NUMFUNC),
                            NUMVINC :Integer.parseInt(it.NUMVINC),
                            DTINI :it.DTINI?format.parse(it.DTINI):null,
                            DTFIM :it.DTFIM?format.parse(it.DTFIM):null,
                            MOTIVO :it.MOTIVO,
                            DTPREVFIM :it.DTPREVFIM?format.parse(it.DTPREVFIM):null
                            )
                    dadosAf.save(data)
                }
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
}
