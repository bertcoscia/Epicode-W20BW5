package bertcoscia.Epicode_W20BW5.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "stati_fatture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatoFattura {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nome_stato")
    private String nomeStato;
}
