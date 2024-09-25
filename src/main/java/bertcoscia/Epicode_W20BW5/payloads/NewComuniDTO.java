package bertcoscia.Epicode_W20BW5.payloads;

import jakarta.validation.constraints.NotEmpty;

public record NewComuniDTO(
        @NotEmpty(message = "Progressivo provincia obbligatorio")
        String progressivoProvincia,
        @NotEmpty(message = "Progressivo comune obbligatorio")
        String progressivoComune,
        @NotEmpty(message = "Nome comune obbligatorio")
        String nome,
        @NotEmpty(message = "Nome provincia obbligatorio")
        String nomeProvincia
) {
}
