package bertcoscia.Epicode_W20BW5.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "fatture")
public class Fattura {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "importo")
    private Double importo;

    @Column(name = "numero_fattura", unique = true)
    private Long numeroFattura;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato_fattura")
    private StatoFattura statoFattura;

    @ManyToOne
    @JoinColumn(name = "id_CLIENTE", referencedColumnName = "id")
    private Cliente cliente;

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getImporto() {
        return importo;
    }

    public void setImporto(Double importo) {
        this.importo = importo;
    }

    public Long getNumeroFattura() {
        return numeroFattura;
    }

    public void setNumeroFattura(Long numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    public StatoFattura getStatoFattura() {
        return statoFattura;
    }

    public void setStatoFattura(StatoFattura statoFattura) {
        this.statoFattura = statoFattura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
