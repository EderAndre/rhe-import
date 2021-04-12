package br.rs.gov.defensoria.rhe.utils

import com.xlson.groovycsv.CsvParser

import java.text.*

import org.springframework.stereotype.Service

@Service
class FileUtils {

    def parseFileCsv (String uri) {
        def file=new FileReader(uri)
        def data = new CsvParser().parse(file, separator: ';')
        return data
    }

    def normalizeFileTxt (String uri, List limitColumns, List headerLine) {
        def file=new FileReader(uri)
        def list=[]
        list.push(headerLine.join(";"))
        for(line in file){
            line=line.replaceAll("\"","-").replaceAll("////","-")
            def temp=[]
            def inicio=0
            limitColumns.each{
                temp.push("\""+line.substring(inicio,it).trim()+"\"")
                inicio=it
            }
            list.push(temp.join(";"))
        }
        return list.join("\n")
    }

    def normalizeFileCsv (String uri) {
        def file=new FileReader(uri)
        def list=[]
        for(line in file){
            def temp=[]
            def inicio=0
            temp.push(line.replaceAll("\"",""))

            list.push(temp.join(";"))
        }
        return list.join("\n")
    }

    def createFile(String filename,String content){
        def result
        try{
            PrintWriter writer = new PrintWriter(filename)
            writer.print(content)
            writer.close()
            result=filename+" criado com sucesso."
        } catch (IOException e) {
            result=filename+" não pode ser criado:"+e
            // do something
        }
        return result
    }

    def copyFile(String uriOrigin, String uriDestiny){
        def ret="Falha ao iniciar cópia"
        try{
            def text=new File(uriOrigin).getText('windows-1252')
            new File(uriDestiny).write(text,'UTF-8')
            ret=uriOrigin+" - cópia bem sucedida"
        }
        catch(IOException e){
            ret="Ocorreu um erro ao copiar: "+e
        }
        return ret
    }

    def createDirectory(String UriToNewDirectory){
        def folder = new File( UriToNewDirectory )
        def ret
        if( !folder.exists() ) {
            ret=  folder.mkdirs()
        }else{
            ret ="A pasta já existe"
        }
        return ret
    }

    def createDirectoryToday(String UriToNewDirectory){
        SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd")
        def today= format.format(new Date())
        return createDirectory(UriToNewDirectory+File.separator+today)
    }

    private def analyzeAndCorrectYearOfDate(String dateParseable){
        SimpleDateFormat  format2Y = new SimpleDateFormat("dd/MM/yy")
        SimpleDateFormat  format4Y = new SimpleDateFormat("dd/MM/yyyy")
        def correctDate=null
        
        try {
        correctDate=dateParseable?format4Y.parse(dateParseable):null
        correctDate=correctDate?format2Y.parse(dateParseable):null
        }
        catch(Exception pe){
           System.out.println("Data Inválida"+pe);
        }
        return correctDate
    }

}
