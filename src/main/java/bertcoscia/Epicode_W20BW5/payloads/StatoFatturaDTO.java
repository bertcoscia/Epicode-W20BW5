package bertcoscia.Epicode_W20BW5.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record StatoFatturaDTO(@NotEmpty(message = "Il nome è obbligatoria")
                              @Size(min = 3, max = 40)
                              String nomeStato) {
}
