package br.rs.gov.defensoria.rhe.controller

import java.text.SimpleDateFormat

import javax.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import br.rs.gov.defensoria.rhe.service.*
import br.rs.gov.defensoria.rhe.utils.UriDiscovery

@RestController
@Transactional
@RequestMapping(value="/rhe")
public class RunnerController {

    @Autowired
    private MailService mailSender1

    @Autowired
    private UriDiscovery directory

    @Autowired
    private FilesService filesSv

    @Autowired
    private MatchImporter importer


    @Value('${apply.files.directory.rhe.normalized}')
    private String filesDirectoryNormalized

    @RequestMapping(value="/import", method=RequestMethod.GET)
    public importDataController(HttpServletResponse response
    )throws Exception{
        return ['É necessário definir uma data no formato yyyyMMdd, como em domain.com/rhe/import/20170101']
    }

    @RequestMapping(value="/import/{date}", method=RequestMethod.GET)
    public importDataFromDayController(@PathVariable String date,HttpServletResponse response
    )throws Exception{
        SimpleDateFormat  outputDate = new SimpleDateFormat("yyyyMMdd")
        def dateParsed=outputDate.parse(date)
        def pathName= outputDate.format(dateParsed).toString()
        def pathNormal= filesDirectoryNormalized+File.separator+pathName
        def retorno=[]
        def listNormalized=new File(pathNormal).listFiles()
        def msg=''
        try{
            if(listNormalized.findAll().size>0){
                listNormalized.each {
                    retorno.push(
                        importer.dispatchFile("$it")
                        )
                }
                msg=retorno.join("\n")
                retorno.push(mailSender1.dispatch(msg))
            }
            else{
                retorno.push("Erros: \n * A pasta de arquivos normalizados está vazia ou não foi criada. \n * Possivelmente não foram disponibilizados arquivos de atualização para este dia ")
                msg=retorno.join("\n")
                retorno.push(mailSender1.dispatch(msg,false))
            }
            
        }
        catch(Exception e){
            msg="Ocorreu um erro:\n"+e
            retorno.push(msg)
            retorno.push(mailSender1.dispatch(msg,false))
        }

            
           
            return retorno
    }
}
