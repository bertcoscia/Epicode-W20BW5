package bertcoscia.Epicode_W20BW5.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewProvinceDTO(
        @NotEmpty(message = "Nome provincia obbligatorio")
        String nome,
        @NotEmpty(message = "Sigla provincia obbligatoria")
        @Size(min = 2, max = 2, message = "La sigla deve contenere due caratteri")
        String sigla,
        @NotEmpty(message = "Regione obbligatoria")
        String regione
) {
}
