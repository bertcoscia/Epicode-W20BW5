package bertcoscia.Epicode_W20BW5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "province")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Provincia {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private UUID idProvincia;
    private String sigla;
    private String regione;

    public Provincia(String sigla, String regione) {
        this.sigla = sigla;
        this.regione = regione;
    }
}
