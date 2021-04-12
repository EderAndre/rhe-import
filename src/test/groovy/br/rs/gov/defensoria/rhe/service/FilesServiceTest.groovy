package br.rs.gov.defensoria.rhe.service

import static org.junit.Assert.*
import br.rs.gov.defensoria.rhe.service.FilesService
import br.rs.gov.defensoria.rhe.utils.TypesDefinition
import br.rs.gov.defensoria.rhe.utils.UriDiscovery
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class FilesServiceTest {

    @Autowired
    FilesService test=new FilesService()

    private def list= new UriDiscovery().listFilesByDate(
    this.getClass().getResource('/stubs/raw/').getFile(),
    "20170116")
    @Test
    public void testDetectTypeAfastamentos() {
        assertEquals('Afastamentos',test.detectType(list[0].getName())?.name,)
    }

    @Test
    public void testDetectDadosPublicacao() {
        assertEquals('DadosPublicacao',test.detectType(list[1].getName())?.name,)
    }

    @Test
    public void testDetectDadosEventos() {
        assertEquals('DadosEventos',test.detectType(list[2].getName())?.name,)
    }

    @Test
    public void testDetectDadosFuncionais() {
        assertEquals('DadosFuncionais',test.detectType(list[3].getName())?.name,)
    }

    @Test
    public void testDetectMestreSetor() {
        assertEquals('MestreSetorHistorico',test.detectType(list[4].getName())?.name,)
    }

    @Test
    public void testDetectDadosVinculo() {
        assertEquals('DadosVinculo',test.detectType(list[5].getName())?.name,)
    }
    @Test
    public void testDetectDadosEventDeleted() {
        assertEquals('Eventos Exluídos',test.detectType(list[6].getName())?.name,)
    }


}
