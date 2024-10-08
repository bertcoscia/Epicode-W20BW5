package bertcoscia.Epicode_W20BW5.payloads;

import bertcoscia.Epicode_W20BW5.enums.TipoCliente;
import jakarta.validation.constraints.*;

public record NewClienteDTO(
        @NotEmpty(message = "Il nome della società non può essere vuoto")
        @Size(min = 3, max = 100, message = "Il nome della società deve essere compreso tra 3 e 100 caratteri")
        String nomeSocieta,

        @NotEmpty(message = "La partita IVA non può essere vuota")
        @Size(min = 11, max = 11, message = "La partita IVA deve essere esattamente di 11 caratteri")
        String partitaIva,

        @NotEmpty(message = "L'email non può essere vuota")
        @Email(message = "L'email inserita non è valida")
        String email,

        @NotEmpty(message = "Data di inserimento obbligatoria")
        String dataInserimento,

        @NotEmpty(message = "Data ultimo contatto obbligatoria")
        String dataUltimoContatto,

        @NotNull(message = "Fatturato annuale obbligatorio")
        @Positive
        double fatturatoAnnuale,

        @NotEmpty(message = "La PEC non può essere vuota")
        @Email(message = "Formato email non valido")
        String pec,

        @NotEmpty(message = "Il numero di telefono non può essere vuoto")
        @Size(min = 10, max = 15, message = "Il numero di telefono deve essere compreso tra 10 e 15 cifre")
        String telefono,

        @NotEmpty(message = "L'email del contatto non può essere vuota")
        @Email(message = "L'email del contatto non è valida")
        String emailContatto,

        @NotEmpty(message = "Il nome del contatto non può essere vuoto")
        @Size(min = 2, max = 50, message = "Il nome del contatto deve essere compreso tra 2 e 50 caratteri")
        String nomeContatto,

        @NotEmpty(message = "Il cognome del contatto non può essere vuoto")
        @Size(min = 2, max = 50, message = "Il cognome del contatto deve essere compreso tra 2 e 50 caratteri")
        String cognome,

        @NotEmpty(message = "Il telefono del contatto non può essere vuoto")
        @Size(min = 10, max = 15, message = "Il numero di telefono del contatto deve essere compreso tra 10 e 15 cifre")
        String telefonoContatto,

        @NotEmpty(message = "Tipologia cliente obbligatoria")
        String tipoCliente,

        @NotEmpty(message = "Indirizzo sede legale obbligatorio")
        String sedeLegale,

        @NotEmpty(message = "Indirizzo sede operativa obbligatorio")
        String sedeOperativa
) {
}