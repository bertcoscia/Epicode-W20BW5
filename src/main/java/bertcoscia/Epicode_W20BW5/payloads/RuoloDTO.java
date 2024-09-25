package bertcoscia.Epicode_W20BW5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RuoloDTO(@NotEmpty(message = "Il nome è obbligatoria")
                       @Email(message = "Il nome non è valido!")
                       String nome) {
}
