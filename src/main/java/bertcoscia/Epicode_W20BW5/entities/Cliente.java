package bertcoscia.Epicode_W20BW5.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "clienti")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nome_societa")
    private String nomeSocieta;
}
