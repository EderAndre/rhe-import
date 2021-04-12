package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import org.springframework.stereotype.Service
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import br.rs.gov.defensoria.rhe.repository.entity.DadosFuncionaisEntity
import br.rs.gov.defensoria.rhe.repository.DadosFuncionaisRepository
import br.rs.gov.defensoria.rhe.utils.FileUtils

@Service
class ImportDadosFuncionaisFromCsvFile {

    @Autowired
    DadosFuncionaisRepository dadosFuncionaisRepository

    @Autowired
    FileUtils fileUtils

    @Transactional
    private String importData(String fileName){

        String message
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yy")
        try {
            def  res= fileUtils.parseFileCsv(fileName)
            res?.each{
                String dtnasc=it.DTNASC
                def data=new DadosFuncionaisEntity(
                        numfunc :Integer.parseInt(it.NUMFUNC),
                        numvinc :Integer.parseInt(it.NUMVINC),
                        CPF :it.CPF,
                        NOME :it.NOME,
                        NUMRG :it.NUMRG,
                        UFRG :it.UFRG,
                        SEXO :it.SEXO,
                        DTNASC :format.parse(analyzeAndCorrectDateOfBirth(dtnasc)),
                        PAI :it.PAI,
                        MAE :it.MAE,
                        ESTCIVIL :it.ESTCIVIL,
                        ESCOLARIDADE :it.ESCOLARIDADE,
                        SETOR :it.SETOR,
                        SETOR_LOTACAO :it.SETOR_LOTACAO ,
                        NOMELOGENDER :it.NOMELOGENDER ,
                        TIPOLOGENDER :it.TIPOLOGENDER ,
                        NUMENDER :it.NUMENDER ,
                        BAIRROENDER :it.BAIRROENDER ,
                        CEPENDER :it.CEPENDER ,
                        COMPLENDER :it.COMPLENDER ,
                        UFENDER :it.UFENDER ,
                        CIDADEENDER :it.CIDADEENDER ,
                        TELEFONE :it.TELEFONE ,
                        NUMTEL_CELULAR :it.NUMTEL_CELULAR ,
                        NACIONALIDADE :it.NACIONALIDADE ,
                        DTVAC :it.DTVAC ,
                        DTAPOSENT :it.DTAPOSENT ,
                        TIPOVINC :it.TIPOVINC ,
                        REGIMEJUR :it.REGIMEJUR ,
                        DTEXERC :it.DTEXERC ,
                        DTPOSSE :it.DTPOSSE ,
                        DTNOM :it.DTNOM ,
                        MATRICULA :it.MATRICULA ,
                        E_MAIL :it.E_MAIL ,
                        BANCO :it.BANCO ,
                        AGENCIA :it.AGENCIA ,
                        CONTA :it.CONTA ,
                        PISPASEP :it.PISPASEP ,
                        COD_CIDNASC :it.COD_CIDNASC ,
                        COD_CIDENDER :it.COD_CIDENDER ,
                        DTINI_CESSAO :it.DTINI_CESSAO ,
                        DTAFAST :it.DTAFAST ,
                        SIT_RHE :it.SIT_RHE ,
                        G_SANGUINEO :it.G_SANGUINEO ,
                        RACA :it.RACA ,
                        DEFICIENTE :it.DEFICIENTE ,
                        TIPODEFIC :it.TIPODEFIC ,
                        OBSERV :it.OBSERV ,
                        ORGAORG :it.ORGAORG ,
                        EXPEDRG :it.EXPEDRG ,
                        NUMTITEL :it.NUMTITEL ,
                        ZONATITEL :it.ZONATITEL ,
                        SECTITEL :it.SECTITEL ,
                        UFTITEL :it.UFTITEL ,
                        NUMDOCMILI :it.NUMDOCMILI ,
                        DOCMILITAR :it.DOCMILITAR ,
                        SERDOCMILI :it.SERDOCMILI ,
                        CATMILI :it.CATMILI ,
                        UFDOCMILI :it.UFDOCMILI ,
                        FORCA :it.FORCA ,
                        CNH :it.CNH ,
                        CATCNH :it.CATCNH ,
                        ORGEXPCNH :it.ORGEXPCNH ,
                        DATAEXPCNH :it.DATAEXPCNH ,
                        UFCNH :it.UFCNH ,
                        VALIDCNH :it.VALIDCNH ,
                        IDENTPROF :it.IDENTPROF ,
                        TIPOIDPROF  :it.TIPOIDPROF ,
                        UF_IDENTPROF  :it.UF_IDENTPROF ,
                        DTEXPIDENTPROF :it.DTEXPIDENTPROF
                        )
                dadosFuncionaisRepository.save(data)
            }
            message="Importação do arquivo ${fileName} concluída"
        }catch(IOException e){
            message=" ${fileName}erro na importação."+e
        }

        return message
    }
    
    private def analyzeAndCorrectDateOfBirth(String date,String currentYear=new Date().getYear()){
        currentYear=currentYear.substring(currentYear.length()-2) 
        def tmp=date.split('/')
        tmp[2]=tmp[2] > currentYear?'19'+tmp[2]:'20'+tmp[2]
        return tmp.join('/')
    }
}
