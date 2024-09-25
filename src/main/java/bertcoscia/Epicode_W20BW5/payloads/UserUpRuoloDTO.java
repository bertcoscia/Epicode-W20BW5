package bertcoscia.Epicode_W20BW5.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserUpRuoloDTO(@NotEmpty(message = "Il nome del ruolo è obbligatoria")
                             @Size(min = 3, max = 40)
                             String nomeRuolo) {
}
