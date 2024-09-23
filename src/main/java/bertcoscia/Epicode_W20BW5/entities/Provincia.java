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
    private String nome;
    private String sigla;
    private String regione;

    public Provincia(String nome, String sigla, String regione) {
        this.nome = nome;
        this.sigla = sigla;
        this.regione = regione;
    }
}
