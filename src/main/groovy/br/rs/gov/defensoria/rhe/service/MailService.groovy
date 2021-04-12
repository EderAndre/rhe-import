package br.rs.gov.defensoria.rhe.service

import java.text.SimpleDateFormat
import javax.inject.Inject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Component

@Component
class MailService {
    @Autowired
    MailSender mailSender

    @Value('${spring.mail.from}')
    String mailFrom

    @Value('${spring.mail.to}')
    String mailTo
    
    @Value('${spring.mail.to.onFail}')
    String mailToOnFail

    Date dataEmail = new Date()
    
    @Value('${spring.mail.subject}')
    String textSubject
    
    @Value('${spring.mail.subject.sufixOnSuccess}')
    String textSufixOnSuccess
    
    @Value('${spring.mail.subject.sufixOnFail}')
    String textSufixOnFail

    String textMsg=''' 
Resultado de rotina de importação:

    \n\n __DETAIL

    \n\n Importador RHE
    \n Em __DATE
'''

    @Inject
    public EmailSender(MailSender mailSender) {
        this.mailSender = mailSender
    }

    private String dispatch( String details='', boolean success=true )throws MailException{
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        SimpleMailMessage message = new SimpleMailMessage()
        String[] mailsToSend=success?mailTo.split(','):mailToOnFail.split(',')
        String sufix=success?textSufixOnSuccess:textSufixOnFail
        message.setFrom(mailFrom)
        message.setTo(mailsToSend)
        message.setSubject(textSubject+sufix)
        def text=textMsg.
                replaceAll("__DETAIL",details.replaceAll("\\\\","/")).
                replaceAll("__DATE",format.format(dataEmail).toString())
        message.setText(text)
        mailSender.send(message)
        return "Email enviado com sucesso"
    }
}
