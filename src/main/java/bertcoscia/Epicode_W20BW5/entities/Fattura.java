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

    public getNumeroFattura() {
    }

    public getData() {
    }

    public getImporto() {
    }
}
