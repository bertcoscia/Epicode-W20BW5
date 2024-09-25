package bertcoscia.Epicode_W20BW5.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    private Date data;

    private double importo;

    @Column(name = "numero_fattura", unique = true)
    private Long numeroFattura;

    @ManyToOne
    @JoinColumn(name = "id_stato")
    private StatoFattura statoFattura;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Fattura(Cliente cliente, Date data, double importo, StatoFattura statoFattura, Long numeroFattura) {
        this.cliente = cliente;
        this.data = data;
        this.importo = importo;
        this.statoFattura = statoFattura;
        this.numeroFattura = numeroFattura;
    }
}
