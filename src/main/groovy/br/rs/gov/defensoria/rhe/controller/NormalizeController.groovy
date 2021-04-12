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

import br.rs.gov.defensoria.rhe.service.FilesService
import br.rs.gov.defensoria.rhe.utils.FileUtils
import br.rs.gov.defensoria.rhe.utils.UriDiscovery

@RestController
@Transactional
@RequestMapping(value="/rhe/prepare")
public class NormalizeController {

    @Autowired
    private UriDiscovery directory

    @Autowired
    private FileUtils filesOp

    @Autowired
    private FilesService filesSv

    @Value('${apply.files.directory.rhe}')
    private String filesDirectory

    @Value('${apply.files.directory.rhe.copydate}')
    private String filesDirectoryCopy

    @Value('${apply.files.directory.rhe.normalized}')
    private String filesDirectoryNormalized



    @RequestMapping(value="/normalize", method=RequestMethod.GET)
    public importDataController(HttpServletResponse response
    )throws Exception{

        return ["Utilize uma data valida no formato yyyMMdd no final da url: exemplo:domain.com/rhe/prepare/normalize/20170101"]
    }
    @RequestMapping(value="/normalize/{date}", method=RequestMethod.GET)
    public importDataForceDateController(
            @PathVariable String date,
            HttpServletResponse response
    )throws Exception{

        SimpleDateFormat  outputDate = new SimpleDateFormat("yyyyMMdd")
        def dateParsed=outputDate.parse(date)
        def pathName= outputDate.format(dateParsed).toString()
        def pathCopy= filesDirectoryCopy+pathName+File.separator
        def pathNormal= filesDirectoryNormalized+pathName
        def retorno=[]

        def list=directory.listFilesByLastModified(filesDirectory,date)
        if(list.size>0){
            retorno.push(filesOp.createDirectory(pathCopy)?"Pasta de cópia criada com sucesso":"Falha")
            list.each {
                retorno.push(filesOp.copyFile("${it}",pathCopy+"__${pathName}__${it.getName()}"))
            }

            def listCopy=new File(pathCopy).listFiles()
            retorno.push(filesOp.createDirectory(pathNormal)?"Pasta de normalizados criada com sucesso":"Falha")
            listCopy.each {
                def type=filesSv.detectType(it.getName())
                type?
                        retorno.push(filesSv.generateFileNormalized("$it",pathNormal+File.separator+it.getName(),type)):
                        retorno.push(it.getName()+" -> fila não pode ser normalizada-tipo não detectável")
            }
        }
        else{
            retorno.push("Aparentemente não existem arquivos para a data solicitada")
        }
        return retorno
    }
}
