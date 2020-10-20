package disciple.online.portal.scopes.mail.service;

import com.google.common.base.Ticker;
import com.mysql.jdbc.log.Log;
import disciple.online.portal.scopes.general.Initializer;
import disciple.online.portal.scopes.report.entities.GlobalTicket;
import disciple.online.portal.scopes.user.entities.User;
import disciple.online.portal.scopes.user.services.UserService;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;

@Service
public class MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private UserService userService;

    private Session session = null ;

    @Value("${spring.mail.from}")
    private String from;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${this.url}")
    private String url;

    public void notifyUserRegistration(String recepient) throws MessagingException {
        Optional<User> user = userService.findUserByEmail(recepient);
        if (!user.isPresent())
            return;

        LOG.info("Preparing to send Mail");
        createSession();

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress((from)));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Registrierung bei Disciple Online");

            String msg  = "<p>Sehr geehrte(r) "+user.get().getFirstname() + " " + user.get().getLastname() + ",<br>"
                    + "Sie wurden erfolgreicht registriert und können ab jetzt das Portal unbegrenzt benutzen.<br>"
                    + "Mit freundlichen Grüßen.</p><br>"
                    + "Disciple Online"
                    + "<br><br>"
                    + "sie erreichen uns unter: <a href='http://"+ url +"'>Disciple Online Portal</a>";

            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(msg,"UTF-8", "html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mbp);
            message.setContent(multipart);

        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setTimeout(() -> {
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }, 200);

        LOG.info("Message sent succesfully");
    }

    public void notifyDiscipleMakerWhenRecieveAReport(String discipleMail , String discipleMakerMail) throws MessagingException {
        Optional<User> disciple = userService.findUserByEmail(discipleMail);
        Optional<User> discipleMaker = userService.findUserByEmail(discipleMakerMail);
        if (!disciple.isPresent() || !discipleMaker.isPresent())
            return;

        LOG.info("Preparing to send Mail");
        createSession();

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress((from)));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(discipleMakerMail));
            message.setSubject("Sie haben ein Bericht von "+disciple.get().getFirstname()+" "
                    +disciple.get().getLastname()+" bekommen");

            String msg  = "<p>Sehr geehrte(r) "+discipleMaker.get().getFirstname() + " " + discipleMaker.get().getLastname() + ",<br>"
                    + "Sie haben ein Bericht von <span style='font-weight: bold'>" +disciple.get().getFirstname()+" "
                    +disciple.get().getLastname()+"</span> bekommen<br>"
                    + "klicken Sie hier um diese zu sehen: <a href='http://"+ url +"'>Disciple Online Portal</a><br>"
                    + "Mit freundlichen Grüßen.<br>"
                    + "Disciple Online</p>"
                    + "<br><br>"
                    + "sie erreichen uns unter: <a href='http://"+ url +"'>Disciple Online Portal</a>";

            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(msg,"UTF-8", "html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mbp);
            message.setContent(multipart);

        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setTimeout(() -> {
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }, 200);

        LOG.info("Message sent succesfully");
    }

    public void notifyDiscipleMakerOnNewDisciple(String discipleMail , String discipleMakerMail) throws MessagingException {
        Optional<User> disciple = userService.findUserByEmail(discipleMail);
        Optional<User> discipleMaker = userService.findUserByEmail(discipleMakerMail);
        if (!disciple.isPresent() || !discipleMaker.isPresent())
            return;

        LOG.info("Preparing to send Mail");
        createSession();

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress((from)));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(discipleMakerMail));
            message.setSubject("Sie haben einen neuen Nachfolger "+disciple.get().getFirstname()+" "
                    +disciple.get().getLastname()+" bekommen");

            String msg  = "<p>Sehr geehrte(r) "+discipleMaker.get().getFirstname() + " " + discipleMaker.get().getLastname() + ",<br>"
                    + "Sie haben einen neuen Nachfolger <span style='font-weight: bold'>" +disciple.get().getFirstname()+" "
                    +disciple.get().getLastname()+"</span> bekommen. Diese Person hat Sie als Disciplemaker ausgewählt.<br>"
                    + "klicken Sie hier um diese zu sehen: <a href='http://"+ url +"'>Disciple Online Portal</a><br>"
                    + "Mit freundlichen Grüßen.<br>"
                    + "Disciple Online</p>"
                    + "<br><br>"
                    + "sie erreichen uns unter: <a href='http://"+ url +"'>Disciple Online Portal</a>";

            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(msg,"UTF-8", "html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mbp);
            message.setContent(multipart);

        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setTimeout(() -> {
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }, 200);

        LOG.info("Message sent succesfully");
    }

    public void sendResetPasswordMail(User user) throws MessagingException {
        if(user == null) throw new IllegalArgumentException("user should not be null");

        LOG.info("Preparing to send Mail");
        createSession();
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress((from)));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("Disciple Online Passwort zurücksetzen");

            String msg  = "<p>Sehr geehrte(r) "+user.getFirstname() + " " + user.getLastname() + ",<br>"
                    + "ihr Password wurde erfolgreich zurückgesetzt.<br>"
                    + "klicken Sie hier um eine neue einzugeben: <a href='http://"+ url +"/renew-password?token="
                    + user.getValidationToken() +"'>Disciple Online Portal</a><br>"
                    + "Mit freundlichen Grüßen.<br>"
                    + "Disciple Online</p>"
                    + "<br><br>"
                    + "sie erreichen uns unter: <a href='http://"+ url +"'>Disciple Online Portal</a>";

            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(msg,"UTF-8", "html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mbp);
            message.setContent(multipart);

        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setTimeout(() -> {
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }, 200);

        LOG.info("Message sent succesfully");
    }

    public void notifyDiscipleWhenRecieveAMessage(String discipleMail , String discipleMakerMail , GlobalTicket globalTicket) throws MessagingException {
        Optional<User> disciple = userService.findUserByEmail(discipleMail);
        Optional<User> discipleMaker = userService.findUserByEmail(discipleMakerMail);
        if (!disciple.isPresent() || !discipleMaker.isPresent())
            return;

        LOG.info("Preparing to send Mail");
        createSession();

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress((from)));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(discipleMail));
            message.setSubject("Sie haben ein Nachricht von "+discipleMaker.get().getFirstname()+" "
                    +discipleMaker.get().getLastname()+" bekommen");

            String msg  = "<p>Sehr geehrte(r) "+disciple.get().getFirstname() + " " + disciple.get().getLastname() + ",<br>"
                    + "Sie haben ein Nachricht von <span style='font-weight: bold'>" +discipleMaker.get().getFirstname()+" "
                    +discipleMaker.get().getLastname()+" auf das Ticket "+ globalTicket.getWeek()+"</span> bekommen<br>"
                    + "Eine Übersicht zu ihrer Diskussion:<br><br> "
                    + globalTicket.getMessage() + "<br><br>"
                    + "klicken Sie hier um diese zu sehen: <a href='http://"+ url
                    + "/report/edit/" + globalTicket.getId()+"'>Disciple Online Portal</a><br>"
                    + "Mit freundlichen Grüßen.<br>"
                    + "Disciple Online</p>"
                    + "<br><br>"
                    + "sie erreichen uns unter: <a href='http://"+ url +"'>Disciple Online Portal</a>";

            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(msg,"UTF-8", "html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mbp);
            message.setContent(multipart);

        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setTimeout(() -> {
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }, 200);

        LOG.info("Message sent succesfully");
    }

    public void notifyOnAccountDeleted(User disciple) throws MessagingException {

        LOG.info("Preparing to send Mail");
        createSession();

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress((from)));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(disciple.getEmail()));
            message.setSubject("Ihr Konto wurde gelösht");

            String msg  = "<p>Sehr geehrte(r) "+disciple.getFirstname() + " " + disciple.getLastname() + ",<br>"
                    + "Wir haben Ihr Konto gelöscht. Vielen Dank, dass Sie uns Einmal vertraut haben<br>"
                    + "Mit freundlichen Grüßen.<br>"
                    + "Disciple Online</p>"
                    + "<br><br>"
                    + "sie erreichen uns unter: <a href='http://"+ url +"'>Disciple Online Portal</a>";

            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(msg,"UTF-8", "html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mbp);
            message.setContent(multipart);

        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setTimeout(() -> {
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }, 200);

        LOG.info("Message sent succesfully");
    }

    @Scheduled(cron = "0 0 22 * * 7")
    public void weeklyReportNotification(){
        for(User user : userService.getAllDisciple()){
            LOG.info("Preparing to send Mail");
            createSession();
            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress((from)));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                message.setSubject("Haben Sie diese Woche schon Ihre Bericht abgelegt ?");
                String msg  = "<p>Sehr geehrte(r) "+user.getFirstname() + " " + user.getLastname() + ",<br>"
                        + "Haben Sie schon Ihre Bericht von dieser Woche abgelegt ?<br>"
                        + "Wenn nicht , einfach hier klicken und weitermachen : <a href='http://"+ url +"'>Disciple Online Portal</a><br>"
                        + "Mit freundlichen Grüßen.<br>"
                        + "Disciple Online</p>"
                        + "<br><br>"
                        + "sie erreichen uns unter: <a href='http://"+ url +"'>Disciple Online Portal</a>";

                MimeBodyPart mbp = new MimeBodyPart();
                mbp.setText(msg,"UTF-8", "html");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mbp);
                message.setContent(multipart);

                } catch (AddressException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (MessagingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                setTimeout(() -> {
                    try {
                        Transport.send(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }, 200);
            LOG.info("Message sent succesfully");
        };
    }

    private void createSession() {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "false");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.EnableSSL.enable","true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
    }

    private void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
