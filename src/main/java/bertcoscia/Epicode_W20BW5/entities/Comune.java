package bertcoscia.Epicode_W20BW5.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "comuni")
@NoArgsConstructor
@Getter
@Setter
@ToString
//@JsonIgnoreProperties({"progressivoProvincia", "progressivoComune"})
public class Comune {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private UUID idComune;
    @Column(name = "progressivo_provincia")
    private String progressivoProvincia;
    @Column(name = "progressivo_comune")
    private String progressivoComune;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    public Comune(String progressivoProvincia, String progressivoComune, String nome, Provincia provincia) {
        this.progressivoProvincia = progressivoProvincia;
        this.progressivoComune = progressivoComune;
        this.nome = nome;
        this.provincia = provincia;
    }
}
