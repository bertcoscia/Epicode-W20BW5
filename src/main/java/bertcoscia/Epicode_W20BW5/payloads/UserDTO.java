package bertcoscia.Epicode_W20BW5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserDTO(@NotEmpty(message = "Username è obbligatoria")
                      @Email(message = "Username non valida!")
                      String username,
                      @NotEmpty(message = "L'email è obbligatoria")
                      @Email(message = "L'email non valida!")
                      String email,
                      @NotEmpty(message = "La password è obbligatoria")
                      @Email(message = "La password non è valida!")
                      String password,
                      @NotEmpty(message = "Il nome è obbligatoria")
                      @Email(message = "Il nome non è valido!")
                      String nome,
                      @NotEmpty(message = "Il cognome è obbligatoria")
                      @Email(message = "Il cognome non è valido!")
                      String cognome) {
}
