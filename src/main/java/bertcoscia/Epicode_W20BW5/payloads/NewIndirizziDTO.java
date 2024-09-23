package bertcoscia.Epicode_W20BW5.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewIndirizziDTO(
        @NotEmpty(message = "Nome della via obbligatorio")
        String via,
        @NotNull(message = "Numero civico obbligatorio")
        @Min(0)
        int civico,
        @NotEmpty(message = "Località obbligatoria")
        String localita,
        @NotEmpty(message = "Nome del comune obbligatorio")
        String comune
) {
}
