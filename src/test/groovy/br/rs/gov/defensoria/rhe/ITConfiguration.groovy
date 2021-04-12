package br.rs.gov.defensoria.rhe

import br.rs.gov.defensoria.rhe.repository.DadosFuncionaisRepository
import br.rs.gov.defensoria.rhe.service.ImportDadosFuncionaisFromCsvFile
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import br.rs.gov.defensoria.rhe.service.MailService

@Configuration
@EnableAutoConfiguration

@EnableJpaRepositories(value = "br.rs.gov.defensoria.rhe.repository")
@ComponentScan(basePackageClasses = ITConfiguration.class)
@PropertySource("classpath:/application-ITWeb.properties")
@Profile('ITWeb')

class ITConfiguration {
    public MailService mailservice(){
        return new MailService()
    }
}
