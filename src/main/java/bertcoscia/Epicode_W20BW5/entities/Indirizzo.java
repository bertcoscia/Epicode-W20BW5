package bertcoscia.Epicode_W20BW5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "indirizzi")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Indirizzo {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private UUID idIndirizzo;
    private String via;
    private int civico;
    private String localita;
    private String cap;
    @ManyToOne
    @JoinColumn(name = "id_comune")
    private Comune comune;

    public Indirizzo(String via, int civico, String localita, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        Random random = new Random();
        StringBuilder newCap = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            newCap.append(String.valueOf(random.nextInt(0, 9)));
        }
        this.cap = newCap.toString();
        this.comune = comune;
    }
}
