package bertcoscia.Epicode_W20BW5.entities;

import bertcoscia.Epicode_W20BW5.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private TipoCliente tipoCliente;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "clienti_indirizzi",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "indirizzo_id")
    )
    private List<Indirizzi> indirizzi = new ArrayList<>();

    //Costruttore

    public Clienti(String cognome, LocalDate dataInserimento, LocalDate dataUltimoContatto, String email, String emailContatto, double fatturatoAnnuale,
                   String logoAziendale, String nomeContatto, String nomeSocieta, String partitaIva, String pec, String telefono, String telefonoContatto, TipoCliente tipoCliente, List<Indirizzi> indirizzi) {
        this.cognome = cognome;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.email = email;
        this.emailContatto = emailContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.logoAziendale = logoAziendale;
        this.nomeContatto = nomeContatto;
        this.nomeSocieta = nomeSocieta;
        this.partitaIva = partitaIva;
        this.pec = pec;
        this.telefono = telefono;
        this.telefonoContatto = telefonoContatto;
        this.tipoCliente = tipoCliente;
        this.indirizzi = indirizzi;
    }

    //To string

    @Override
    public String toString() {
        return "Clienti{" +
                "cognome='" + cognome + '\'' +
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
                ", indirizzi=" + indirizzi +
                '}';
    }
}
