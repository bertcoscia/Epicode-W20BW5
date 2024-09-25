package bertcoscia.Epicode_W20BW5.entities;

import bertcoscia.Epicode_W20BW5.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clienti")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Clienti {

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
    private String sedeLegale;
    private String sedeOperativa;

    @OneToMany
    private List<Indirizzo> indirizzi;

    //Costruttore

    public Clienti(String cognome, LocalDate dataInserimento, LocalDate dataUltimoContatto, String email, String emailContatto,
                   double fatturatoAnnuale, List<Indirizzo> indirizzi, String logoAziendale, String nomeContatto, String nomeSocieta, String partitaIva, String pec, String sedeLegale, String sedeOperativa, String telefono, String telefonoContatto, TipoCliente tipoCliente) {
        this.cognome = cognome;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.email = email;
        this.emailContatto = emailContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.indirizzi = indirizzi;
        this.logoAziendale = logoAziendale;
        this.nomeContatto = nomeContatto;
        this.nomeSocieta = nomeSocieta;
        this.partitaIva = partitaIva;
        this.pec = pec;
        this.sedeLegale = sedeLegale;
        this.sedeOperativa = sedeOperativa;
        this.telefono = telefono;
        this.telefonoContatto = telefonoContatto;
        this.tipoCliente = tipoCliente;
    }

//To string

    @Override
    public String toString() {
        return "Clienti{" +
                "cognome='" + cognome + '\'' +
                ", id=" + id +
                ", nomeSocieta='" + nomeSocieta + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                ", email='" + email + '\'' +
                ", dataInserimento=" + dataInserimento +
                ", dataUltimoContatto=" + dataUltimoContatto +
                ", fatturatoAnnuale=" + fatturatoAnnuale +
                ", pec='" + pec + '\'' +
                ", telefono='" + telefono + '\'' +
                ", emailContatto='" + emailContatto + '\'' +
                ", nomeContatto='" + nomeContatto + '\'' +
                ", telefonoContatto='" + telefonoContatto + '\'' +
                ", logoAziendale='" + logoAziendale + '\'' +
                ", tipoCliente=" + tipoCliente +
                ", sedeLegale='" + sedeLegale + '\'' +
                ", sedeOperativa='" + sedeOperativa + '\'' +
                ", indirizzi=" + indirizzi +
                '}';
    }
}
