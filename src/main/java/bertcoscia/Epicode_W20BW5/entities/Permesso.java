package bertcoscia.Epicode_W20BW5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "permessi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permesso {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    @ManyToMany(mappedBy = "permessi")
    private List<Ruolo> ruoli = new ArrayList<>();
};
