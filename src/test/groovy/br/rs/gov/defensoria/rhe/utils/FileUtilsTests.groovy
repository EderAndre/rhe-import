package br.rs.gov.defensoria.rhe.utils

import static org.junit.Assert.*

import org.joda.time.LocalDate
import org.junit.Test
import br.rs.gov.defensoria.rhe.utils.FileUtils
import java.text.SimpleDateFormat

class FileUtilsTests {

    @Test
    public void testanalyzeAndCorrectYearOfDate1() {
        def res=new FileUtils().analyzeAndCorrectYearOfDate('')
        assertTrue(res.equals(null))
        
    }
   
    @Test
    public void testanalyzeAndCorrectYearOfDate3() {

         def res3=new FileUtils().analyzeAndCorrectYearOfDate('01/01/2018')
         def comparation=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse('2018-01-01 00:00:00')
        assertEquals(res3,comparation)

    }
    
    @Test
    public void testanalyzeAndCorrectYearOfDate4() {

        def res2=new FileUtils().analyzeAndCorrectYearOfDate('01/01/18')
        def comparation2=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse('2018-01-01 00:00:00')
       assertEquals(res2,comparation2)

    }
  
}
