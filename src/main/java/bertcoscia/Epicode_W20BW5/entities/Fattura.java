package bertcoscia.Epicode_W20BW5.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "fatture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fattura {

    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate data;
    private double importo;

    @Column(name = "numero_fattura", unique = true)
    private Long numeroFattura;

    @ManyToOne
    @JoinColumn(name = "id_stato")
    private StatoFattura statoFattura;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Fattura(Cliente cliente, LocalDate data, double importo, StatoFattura statoFattura) {
        this.cliente = cliente;
        this.data = data;
        this.importo = importo;
        this.statoFattura = statoFattura;
    }
}
