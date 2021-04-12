package br.rs.gov.defensoria.rhe
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ITConfiguration.class)
@ActiveProfiles("ITWeb")
class ApplicationTest {

    @Test
    public void contextLoads() {
    }
}

