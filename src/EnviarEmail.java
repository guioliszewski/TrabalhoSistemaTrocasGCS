import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EnviarEmail {
    public static void enviarEmail(Jogador jogadorLogado, Jogador jogadorRecebe, Item itemProposto, Item itemRecebido) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gcsnotificacaosistema@gmail.com", "tjix xjes mzjk wvmb");
            }
        });

        String assunto = "Você recebeu uma proposta de troca de " + jogadorLogado.getNome();
        String destinatario = jogadorRecebe.getEmail();
        String mensagem = " O jogador " + jogadorLogado.getNome() +
                " deseja trocar " + itemProposto.getNome() + " por " + itemRecebido.getNome();


        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gcsnotificacaosistema@gmail.com"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(assunto);
            message.setText(mensagem, "UTF-8");
            message.setSentDate(new Date());

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Ocorreu erro ao enviar e-mail");
        }
    }
}
