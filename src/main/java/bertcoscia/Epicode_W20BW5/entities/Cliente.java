package bertcoscia.Epicode_W20BW5.entities;

import bertcoscia.Epicode_W20BW5.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "clienti")
@Setter
@Getter
@NoArgsConstructor
@ToString

public class Cliente {

    //Attributi
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private UUID id;
    private String nomeSocieta;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private double fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognome;
    private String telefonoContatto;
    private String logoAziendale;
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;
    @ManyToOne
    @JoinColumn(name = "id_indirizzo_legale")
    private Indirizzo sedeLegale;
    @ManyToOne
    @JoinColumn(name = "id_indirizzo_operativo")
    private Indirizzo sedeOperativa;

    //Costruttore

    public Cliente(String nomeSocieta, String partitaIva, String email, LocalDate dataInserimento, LocalDate dataUltimoContatto, double fatturatoAnnuale, String pec, String telefono, String emailContatto, String nomeContatto, String cognome, String telefonoContatto, String logoAziendale, TipoCliente tipoCliente, Indirizzo sedeLegale, Indirizzo sedeOperativa) {
        this.nomeSocieta = nomeSocieta;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognome = cognome;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = logoAziendale;
        this.tipoCliente = tipoCliente;
        this.sedeLegale = sedeLegale;
        this.sedeOperativa = sedeOperativa;
    }


//To string

}
