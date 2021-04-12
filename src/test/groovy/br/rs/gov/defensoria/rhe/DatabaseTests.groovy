package br.rs.gov.defensoria.rhe

import static org.junit.Assert.*

import br.rs.gov.defensoria.rhe.repository.DadosEventosRepository
import br.rs.gov.defensoria.rhe.repository.DadosFuncionaisRepository
import br.rs.gov.defensoria.rhe.repository.DadosVinculoRepository
import br.rs.gov.defensoria.rhe.repository.MestrePaisRepository
import br.rs.gov.defensoria.rhe.repository.MestreSetorRepository
import br.rs.gov.defensoria.rhe.repository.entity.DadosFuncionaisEntity
import br.rs.gov.defensoria.rhe.repository.pk.FuncionalPK
import br.rs.gov.defensoria.rhe.repository.pk.SetorPK
import br.rs.gov.defensoria.rhe.service.ImportDadosEventosFromCsvFile
import br.rs.gov.defensoria.rhe.service.ImportDadosFuncionaisFromCsvFile
import br.rs.gov.defensoria.rhe.service.ImportDadosVinculoFromCsvFile
import br.rs.gov.defensoria.rhe.service.ImportMestreSetorFromCsvFile
import java.text.SimpleDateFormat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ITConfiguration.class)
@ActiveProfiles('ITWeb')

class DatabaseTests {

    @Autowired
    ImportDadosFuncionaisFromCsvFile importerFunc

    @Autowired
    DadosFuncionaisRepository person

    @Autowired
    MestrePaisRepository pais

    @Autowired
    ImportDadosEventosFromCsvFile importerEv

    @Autowired
    DadosEventosRepository ev

    @Autowired
    ImportMestreSetorFromCsvFile importerSetor

    @Autowired
    MestreSetorRepository setor

    @Autowired
    ImportDadosVinculoFromCsvFile importerVinc

    @Autowired
    DadosVinculoRepository vinc

    @Sql(scripts = [
        'classpath:h2/V1__Create_tables.sql'
        ,
        'classpath:h2/V2__insert_servidores.sql'
    ])
    @Before public void initialize() {
    }

    @Test
    public void testImportDadosFuncionais() {
        importerFunc.importData(this.getClass().getResource('/stubs/normalized/RHE-GOV_DEFPUB-EXPDADOS_COMPL_FUNCIONAL-20170116_NORMALIZED.CSV').getFile())

        def id=new FuncionalPK()
        id.numfunc=4245121
        id.numvinc=1

        def id2=new FuncionalPK()
        id2.numfunc=1009001
        id2.numvinc=1


        assertEquals('94435669072',person.findOne(id).CPF)
        assertEquals('22159240044',person.findOne(id2).CPF)
        assertEquals( 1511, person.findAll().size())
    }

    @Test
    public void testImportDadosSetor() {
        importerSetor.importData(this.getClass().getResource('/stubs/normalized/RHE-GOV_DEFPUB-EXPDADOS_COMPL_SETOR-20170116_NORMALIZED.CSV').getFile())
        def id=new SetorPK()
        id.emp_codigo='1'
        id.setor='300000000000000'

        def id2=new SetorPK()
        id2.emp_codigo='49'
        id2.setor='490130000000012'

        assertEquals('000000000000000',setor.findOne(id).PAISETOR)
        assertEquals('490130000000010',setor.findOne(id2).PAISETOR)
        assertEquals( 635, setor.findAll().size())
    }

    @Test
    public void testImportEventos() {
        importerEv.importData(this.getClass().getResource('/stubs/normalized/RHE-GOV_DEFPUB-EXPDADOS_COMPL_EVENTO-20170116_NORMALIZED.CSV').getFile())

        assertEquals(926566,ev.findOne(101267).NUMFUNC)
        assertEquals(4199928,ev.findOne(3340479).NUMFUNC)
        assertEquals( 2349, ev.findAll().size())
    }

    @Test
    public void testImportVinculos() {
        importerVinc.importData(this.getClass().getResource('/stubs/normalized/RHE-GOV_DEFPUB-EXPDADOS_COMPL_VINCULO-20170116_NORMALIZED.CSV').getFile())

        def id=new FuncionalPK()
        id.numfunc=4245121
        id.numvinc=1

        def id2=new FuncionalPK()
        id2.numfunc=1009001
        id2.numvinc=1

        assertEquals('2015-05-04 00:00:00.0',vinc.findOne(id).DTEXERC.toString())
        assertEquals('1994-05-30 00:00:00.0',vinc.findOne(id2).DTEXERC.toString())
        assertEquals( 1900, vinc.findAll().size())

    }
    @Test
    public void testDateOfBirthAnalyse(){
        def years=[]
        for(int i = 0; i < 100; i++){
            String string='0'+i
            String twoDigits=string.substring(string.length() -2)
            String fourDigits
            String curYear='2017'
            fourDigits= curYear.substring(2) < twoDigits?'19'+twoDigits:'20'+twoDigits
            years.push([twoDigits, fourDigits])
        }
        years.each{
            assertEquals(
                        importerFunc.analyzeAndCorrectDateOfBirth(
                        '01/01/'+it[0],'2017'),
                        '01/01/'+it[1]
                    )
        }
    }


    @Sql(scripts = ['classpath:h2/V3__drops.sql'])
    @After public void finalize() {
    }
}
