package bertcoscia.Epicode_W20BW5.tools;

import bertcoscia.Epicode_W20BW5.entities.Cliente;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailgunSender {
    private String apiKey;
    private String domainName;


    public MailgunSender(@Value("${mailgun.key}") String apiKey,
                         @Value("${mailgun.domain}") String domainName) {
        this.apiKey = apiKey;
        this.domainName = domainName;
    }

    public void sendEmail(Cliente recipient, String subject, String body) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "calopippo429@gmail.com")
                .queryString("to", recipient.getEmail())
                .queryString("subject", subject)
                .queryString("text", body)
                .asJson();

        System.out.println(response.getBody());
    }
}