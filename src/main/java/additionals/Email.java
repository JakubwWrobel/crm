package additionals;

import model.Car;
import model.Order;
import org.w3c.dom.ls.LSOutput;

import javax.mail.*;
import javax.mail.internet.*;
import javax.tools.JavaFileManager;
import java.util.Arrays;
import java.util.Properties;

public class Email {
    public static void sendMail(String recipient, Car car) {

        System.out.println("Preparing mail to send");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        String myAccountEmail = "jakubwrobel22@gmail.com";
        String password = "SALezjanie123";

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient, car);
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private static Message prepareMessage(Session session, String recipient, String myAccountEmail,Car car) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Przegląd samochodu: " + car.getIdNumber());
            message.setText(String.format("Dzień dobry,\n Przypominamy o umówiony przeglądzie samochodu:  %sw dniu: %s w razie zmiany terminu lub brakumożliwości pojawienia się w terminie, prosimy o kontakt telefoniczny. \nZakład Samochodowy, xxx",
                    car.getIdNumber(), car.getNextCheckupDate()));
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }



}

