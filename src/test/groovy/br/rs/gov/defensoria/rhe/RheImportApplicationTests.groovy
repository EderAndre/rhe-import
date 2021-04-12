package br.rs.gov.defensoria.rhe

import org.junit.Test
import static org.junit.Assert.*

import br.rs.gov.defensoria.rhe.utils.FileUtils
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ITConfiguration.class)
@ActiveProfiles('ITWeb')
class RheImportApplicationTests {

    @Test
    public void testParseCsv() {
        def res= new FileUtils().parseFileCsv(this.getClass().getResource('/stubs/normalized/RHE-GOV_DEFPUB-EXPDADOS_COMPL_FUNCIONAL-20170116_NORMALIZED.CSV').getFile())

        def  lvar=res.findAll{ it.NUMFUNC=="4245121"}.CPF
        assertEquals(lvar.toString(),"[94435669072]")
    }
    @Test
    public void testImportFileFuncionalCsvOk() {
        String res=  new FileUtils().normalizeFileCsv(this.getClass().getResource('/stubs/raw/RHE-GOV_DEFPUB-EXPDADOS_COMPL_FUNCIONAL-20170116.CSV').getFile())

        assertEquals(
                res.split("\n").size()     ,
                new File(this.getClass().getResource('/stubs/normalized/RHE-GOV_DEFPUB-EXPDADOS_COMPL_FUNCIONAL-20170116_NORMALIZED.CSV').getFile()).getText().split("\n").size()
                )
    }
}
