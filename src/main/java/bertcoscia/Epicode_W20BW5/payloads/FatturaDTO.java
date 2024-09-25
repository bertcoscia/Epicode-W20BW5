package bertcoscia.Epicode_W20BW5.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record FatturaDTO(@NotEmpty(message = "La data è obbligatorio!")
                         @Size(min = 3, max = 40)
                         String data,
                         @NotNull(message = "L'importo della fattura è obbligatorio")
                         @Positive
                         double importo,
                         @NotEmpty(message = "Lo statoFattura è obbligatorio!")
                         @Size(min = 3, max = 40)
                         String nomeStatoFattura,
                         @NotEmpty(message = "L'id del cliente è obbligatorio!")
                         @Size(min = 3, max = 40)
                         String clienteId) {
}