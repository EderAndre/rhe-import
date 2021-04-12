package br.rs.gov.defensoria.rhe.utils

import java.text.SimpleDateFormat
import org.springframework.stereotype.Component

@Component
class UriDiscovery {

    public def listFiles(String uri, String pattern){
        File path = new File(uri)
        List fileNames=[]
        path.eachFileMatch ( ~/$pattern/) { it ->
            fileNames.push(it)
        }
        return fileNames
    }

    public def listFilesByDate(String PathUri, String date){
        def retorno=[]
        try{
            File path = new File(PathUri)
            String expression=".*-"+date+".*"
            retorno=listFiles(PathUri, expression)
        }catch(IOException e){
        }
        return retorno
    }
    public def listFilesByLastModified(String PathUri, String date){
        def retorno=[]
        SimpleDateFormat  format = new SimpleDateFormat("yyyyMMdd")
        def dayRequired= format.parse(date)
        try{
            new File(PathUri).listFiles().toList().findAll().each{
                def dateModifiedFile=format.format(new Date(it.lastModified()))
                if(date==dateModifiedFile){ retorno.push(it)}
            }
        }catch(IOException e){
        }
        retorno.size()!=0?true:retorno.push('Não existem arquivos incluídos ou modificados nesta data')
        return retorno
    }

    public def listFilesByToday(String PathUri){
        SimpleDateFormat  format = new SimpleDateFormat("yyyyMMdd")
        def today= format.format(new Date())
        return listFilesByDate(PathUri,today)
    }
}
