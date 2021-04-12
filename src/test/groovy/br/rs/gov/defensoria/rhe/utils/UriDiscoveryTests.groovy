package br.rs.gov.defensoria.rhe.utils

import static org.junit.Assert.*
import org.junit.Test
import br.rs.gov.defensoria.rhe.utils.UriDiscovery

class UriDiscoveryTests {

    @Test
    public void testUriDiscovery() {
        def res=new UriDiscovery().listFiles(this.getClass().getResource('/stubs/raw/').getFile(),".*FUNCIONAL-.\\d{6}.\\.CSV")
        assertTrue(res[0].getName().contains('RHE-GOV_DEFPUB-EXPDADOS_COMPL_FUNCIONAL-20170116.CSV'))
    }

    @Test
    public void testListFilesByDate() {
        def res=new UriDiscovery().listFilesByDate(
                this.getClass().getResource('/stubs/raw/').getFile(),"20170116")
        assertTrue(res[0].getName().contains('RHE-GOV_DEFPUB-EXPDADOS_AFAST-20170116_070844.TXT')             )
    }

   // @Test
    public void testListFilesByLastModified() {
        def res=new UriDiscovery().listFilesByLastModified(
            this.getClass().getResource('/stubs/raw/').getFile(),"20170214")
     
        assertTrue(res[0].getAt('name').contains('RHE-GOV_DEFPUB-EXPDADOS_AFAST-20170116_070844.TXT'))


        }
}
